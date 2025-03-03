package com.juanbai.examplespringbootprovider.service.impl;

import com.juanbai.example.common.module.Order;
import com.juanbai.example.common.module.OrderVO;
import com.juanbai.example.common.module.Result;
import com.juanbai.example.common.service.OrderService;

import java.util.List;

/**
 * @author ALL
 * @date 2025/2/8
 * @Description
 */
public class OrderServiceImpl implements OrderService {


    @Override
    public Result<OrderVO> save(Order order) {
        return null;
    }

    @Override
    public Result<OrderVO> findById(Long id) {
        return null;
    }

    @Override
    public Result<List<OrderVO>> findAll() {
        return null;
    }

    @Override
    public Result<List<OrderVO>> findByUserId(Long userId) {
        return null;
    }

    @Override
    public Result<List<OrderVO>> findByUserIdAndStatus(Long userId, Integer status) {
        return null;
    }

    @Override
    public Result<List<OrderVO>> findByUserIdAndStatus(Long userId, Integer status, Integer pageNo, Integer pageSize) {
        return null;
    }

    @Override
    public Result<OrderVO> update(OrderVO order) {
        return null;
    }

    @Override
    public Result<String> delete(Long id) {
        return null;
    }
}
