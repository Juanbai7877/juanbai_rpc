package com.juanbai.example.common.service;

import com.juanbai.example.common.module.Order;
import com.juanbai.example.common.module.OrderVO;
import com.juanbai.example.common.module.Result;

import java.util.List;

/**
 * @author ALL
 * @date 2025/2/8
 * @Description
 */
public interface OrderService {
    Result<OrderVO> save(Order order);
    Result<OrderVO> findById(Long id);
    Result<List<OrderVO>> findAll();
    Result<List<OrderVO>> findByUserId(Long userId);
    Result<List<OrderVO>> findByUserIdAndStatus(Long userId, Integer status);
    Result<List<OrderVO>> findByUserIdAndStatus(Long userId, Integer status, Integer pageNo, Integer pageSize);
    Result<OrderVO> update(OrderVO order);
    Result<String> delete(Long id);
}
