package com.exercise.navent.com.exercise.navent.service;

import com.exercise.navent.com.exercise.navent.cache.BumexMemcached;
import com.exercise.navent.com.exercise.navent.dao.PedidosDAO;
import com.exercise.navent.com.exercise.navent.entity.Pedido;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final BumexMemcached bumexMemcached = BumexMemcached.getInstance();

    @Override
    public Pedido crearPedido(Pedido pedido) {
        PedidosDAO.insertOrUpdate(pedido);
        return pedido;
    }

    @Override
    public Pedido modificarPedido(Pedido pedido) {
        PedidosDAO.insertOrUpdate(pedido);
        Object obj = bumexMemcached.get(pedido.getId().toString());
        if (obj != null) {
            bumexMemcached.set(pedido.getId().toString(), (Object) pedido);
        }
        return pedido;
    }

    @Override
    public Pedido buscarPedidoPorId(Integer id) {
        Object obj = bumexMemcached.get(id.toString());
        if (obj == null) {
            obj = PedidosDAO.select(id);
            bumexMemcached.set(id.toString(), obj);
        }
        return (Pedido) obj;
    }


    @Override
    public void borrarPedido(Pedido pedido) {
        Object obj = bumexMemcached.get(pedido.getId().toString());
        if (obj != null) {
            bumexMemcached.delete(pedido.getId().toString());
        }
        PedidosDAO.delete(pedido);
    }

}
