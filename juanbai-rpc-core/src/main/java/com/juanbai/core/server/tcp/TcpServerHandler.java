package com.juanbai.core.server.tcp;

import com.juanbai.core.model.RpcRequest;
import com.juanbai.core.model.RpcResponse;
import com.juanbai.core.protocol.*;
import com.juanbai.core.registry.LocalRegistry;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetSocket;
import com.juanbai.core.model.ServiceMetaInfo;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * TCP 请求处理器
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @learn <a href="https://codefather.cn">编程宝典</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
public class TcpServerHandler implements Handler<NetSocket> {

    /**
     * 处理请求
     *
     * @param socket the event to handle
     */
    @Override
    public void handle(NetSocket socket) {
        TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
            // 接受请求，解码
            ProtocolMessage<RpcRequest> protocolMessage;
            try {
                protocolMessage = (ProtocolMessage<RpcRequest>) ProtocolMessageDecoder.decode(buffer);
            } catch (IOException e) {
                throw new RuntimeException("协议消息解码错误");
            }
            RpcRequest rpcRequest = protocolMessage.getBody();
            ProtocolMessage.Header header = protocolMessage.getHeader();

            // 处理请求
            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            long startTime = System.currentTimeMillis();
            long endTime;
            try {
                // 获取要调用的服务实现类，通过反射调用
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
                // 记录调用时间
                endTime = System.currentTimeMillis();
                long[] timeRecord = new long[]{
                        startTime,endTime
                };

            }

            // 发送响应，编码
            header.setType((byte) ProtocolMessageTypeEnum.RESPONSE.getKey());
            header.setStatus((byte) ProtocolMessageStatusEnum.OK.getValue());
            ProtocolMessage<RpcResponse> responseProtocolMessage = new ProtocolMessage<>(header, rpcResponse);
            try {
                Buffer encode = ProtocolMessageEncoder.encode(responseProtocolMessage);
                socket.write(encode);
            } catch (IOException e) {
                endTime = System.currentTimeMillis();
                long[] timeRecord = new long[]{
                        startTime,endTime
                };
                throw new RuntimeException("协议消息编码错误");
            }
            // 记录调用耗时
            endTime = System.currentTimeMillis();
            long[] timeRecord = new long[]{
                    startTime,endTime
            };
        });
        socket.handler(bufferHandlerWrapper);
    }

}
