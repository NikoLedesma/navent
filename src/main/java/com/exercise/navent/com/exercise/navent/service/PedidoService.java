package com.exercise.navent.com.exercise.navent.service;

import com.exercise.navent.com.exercise.navent.entity.Pedido;

public interface PedidoService {

    Pedido crearPedido(Pedido pedido);

    Pedido modificarPedido(Pedido pedido);

    Pedido buscarPedidoPorId(Integer id);

    void borrarPedido(Pedido pedido);

}