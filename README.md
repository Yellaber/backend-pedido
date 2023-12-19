# Backend para Gestionar Pedidos
Este repositorio contiene las APIs relacionadas con la gestión de pedidos, empleando JPA para la persistencia y la relación entre sus entidades.

## Entidades
Catalogo, Categoria, Cliente, Cuenta, ItemPedido, Movimiento, Pago, Pedido, Producto, Proveedor y Vendedor.

## Request de las APIs
* Para Catalogo: `/api/catalogo`
* Para Categoria: `/api/categoria`
* Para Cliente: `/api/cliente`
* Para Cuenta: `/api/cuenta`
* Para ItemPedido: `/api/item`
* Para Movimiento: `/api/movimiento`
* Para Pago: `/api/pago`
* Para Pedido: `/api/pedido`
* Para Producto: `/api/producto`
* Para Proveedor: `/api/proveedor`
* Para Vendedor: `/api/vendedor`

## Formato de las Response
Todas las Request de tipo `GET`, `POST` y `PUT` y errores son de tipo `JSON`.

## Estados HTTP de las Response
* `200` para las peticiones de tipo `GET`.
* `201` para las peticiones de tipo `POST` y `PUT`.
* `204` para cuando se ha eliminado un registro con peticiones de tipo `DELETE`.
* `400` para cuando se envia una mala petición.
* `404` para cuando no se encuentra el registro solicitado en peticiones de tipo `GET`, `PUT` y `DELETE`.

## Modelo ER
