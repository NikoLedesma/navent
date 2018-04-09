package com.exercise.navent.com.exercise.navent.controller;


import com.exercise.navent.com.exercise.navent.entity.Pedido;
import com.exercise.navent.com.exercise.navent.exception.PedidoNotFoundException;
import com.exercise.navent.com.exercise.navent.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @RequestMapping(method = RequestMethod.POST,value = "guardar")
    public Pedido crearPedido(@RequestBody Pedido pedido,HttpServletResponse response ){
        response.setStatus(HttpServletResponse.SC_CREATED);
        this.pedidoService.crearPedido(pedido);
        return pedido;
    }

    @RequestMapping(method = RequestMethod.PUT)

    public Pedido modificarPedido(@RequestBody Pedido pedido){
        return this.pedidoService.modificarPedido(pedido);
    }

    @RequestMapping(method = RequestMethod.GET,value="/{id}")
    public Pedido buscarPedidoPorId(@PathVariable Integer id) {
        Pedido pedido = this.pedidoService.buscarPedidoPorId(id);
        if(pedido == null){
            throw new PedidoNotFoundException("Pedido no encontrado");
        }
        return pedido;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void borrarPedido(@RequestBody Pedido pedido,HttpServletResponse response){
        this.pedidoService.borrarPedido(pedido);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
