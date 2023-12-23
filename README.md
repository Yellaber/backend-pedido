# Backend para Gestionar Pedidos
Este repositorio contiene la API relacionada con la gestión de pedidos, empleando **JPA** para la persistencia, la relación entre sus entidades y sus correspondientes metodos CRUD.

## Entidades
Catalogo, Categoria, Cliente, Cuenta, ItemPedido, Mesa, Movimiento, Pago, Pedido, Producto y Proveedor.

## Request de las APIs
* Para Catalogo: `/api/catalogo`
* Para Categoria: `/api/categoria`
* Para Cliente: `/api/cliente`
* Para Cuenta: `/api/cuenta`
* Para ItemPedido: `/api/item`
* Para Mesa: `/api/mesa`
* Para Movimiento: `/api/movimiento`
* Para Pago: `/api/pago`
* Para Pedido: `/api/pedido`
* Para Producto: `/api/producto`
* Para Proveedor: `/api/proveedor`

## Formato de las Response
Las Response de las Request de tipo `GET`, `POST`, `PUT` y `DELETE` así como los errores son de tipo `JSON`.

## Estados HTTP de las Response
* `200` para las peticiones de tipo `GET`.
* `201` para las peticiones de tipo `POST` y `PUT`.
* `204` para cuando se ha eliminado un registro con peticiones de tipo `DELETE`.
* `400` para cuando se envia una mala petición de tipo `POST`.
* `404` para cuando no se encuentra el registro solicitado en peticiones de tipo `GET`, `PUT` y `DELETE`.

## Modelo ER
![Modelo ER de la BD para gestionar pedidos](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/modelo-er-backend-pedido.png)
