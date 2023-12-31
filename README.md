# Backend para Gestionar Pedidos
Este repositorio contiene la API para la gestión de pedidos de un Bar-Restaurante, desarrollado bajo `Spring Framework` empleando **JPA** para la persistencia, la relación entre sus entidades y sus correspondientes metodos CRUD. Como **SGBD** se utilizó `MySQL`.

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

## Algunas pruebas realizadas con Postman
Para las pruebas se creó una variable de entorno `URL` que contiene la dirección base para los endpoints `http://localhost:8080/api`.

### Prueba para Catalogo
* Operación CRUD: `CREAR`. Tipo de request: `POST`. Endpoint: `{{URL}}/catalogo`.
  ![Prueba en Postman para Crear Catalogo](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-crear-catalogo.png)
  
* Operación CRUD: `LISTAR`. Tipo de request: `GET`. Endpoint: `{{URL}}/catalogo`.
  ![Prueba en Postman para Obtener Catalogos](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-catalogos.png)
  
* Operación CRUD: `OBTENER`. Tipo de request: `GET`. Endpoint: `{{URL}}/catalogo/{id}`.
  ![Prueba en Postman para Obtener Catalogo por ID](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-catalogo-id.png)
  
* Operación CRUD: `ACTUALIZAR`. Tipo de request: `PUT`. Endpoint: `{{URL}}/catalogo/{id}`.
  ![Prueba en Postman para Actualizar Catalogo por ID](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-actualizar-catalogo-id.png)

### Prueba para Categoria
* Operación CRUD: `CREAR`. Tipo de request: `POST`. Endpoint: `{{URL}}/categoria`.
  ![Prueba en Postman para Crear Categoria 5](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-crear-categoria-1.png)

  ![Prueba en Postman para Crear Categoria 6](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-crear-categoria-2.png)
  
* Operación CRUD: `LISTAR`. Tipo de request: `GET`. Endpoint: `{{URL}}/categoria`.
  ![Prueba en Postman para Obtener Categorias](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-categorias.png)
  
* Operación CRUD: `OBTENER`. Tipo de request: `GET`. Endpoint: `{{URL}}/categoria/{id}`.
  ![Prueba en Postman para Obtener Categoria por ID](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-categoria-id-1.png)

### Prueba para Cliente
* Operación CRUD: `CREAR`. Tipo de request: `POST`. Endpoint: `{{URL}}/cliente`.
  ![Prueba en Postman para Crear Cliente](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-crear-cliente.png)
  
* Operación CRUD: `LISTAR`. Tipo de request: `GET`. Endpoint: `{{URL}}/cliente`.
  ![Prueba en Postman para Obtener Clientes](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-clientes.png)
  
* Operación CRUD: `OBTENER`. Tipo de request: `GET`. Endpoint: `{{URL}}/cliente/{cedula}`.
  ![Prueba en Postman para Obtener Cliente por Cédula](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-cliente-cedula.png)
  
* Operación CRUD: `ACTUALIZAR`. Tipo de request: `PUT`. Endpoint: `{{URL}}/cliente/{id}`.
  ![Prueba en Postman para Actualizar Cliente por ID](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-actualizar-cliente-id.png)

### Prueba para Mesa
* Operación CRUD: `CREAR`. Tipo de request: `POST`. Endpoint: `{{URL}}/mesa`.
  ![Prueba en Postman para Crear Mesa](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-crear-mesa.png)
  
* Operación CRUD: `LISTAR`. Tipo de request: `GET`. Endpoint: `{{URL}}/mesa`.
  ![Prueba en Postman para Obtener Mesas](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-mesas.png)
  
* Operación CRUD: `OBTENER`. Tipo de request: `GET`. Endpoint: `{{URL}}/mesa/{id}`.
  ![Prueba en Postman para Obtener Mesa por ID](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-mesa-id.png)
  
* Operación CRUD: `ACTUALIZAR`. Tipo de request: `PUT`. Endpoint: `{{URL}}/mesa/{id}`.
  ![Prueba en Postman para Actualizar Mesa por ID](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-actualizar-mesa-id.png)

### Prueba para Producto
* Operación CRUD: `CREAR`. Tipo de request: `POST`. Endpoint: `{{URL}}/producto`.
  ![Prueba en Postman para Crear Producto](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-crear-producto.png)
  
* Operación CRUD: `LISTAR`. Tipo de request: `GET`. Endpoint: `{{URL}}/producto`.
  ![Prueba en Postman para Obtener Productos](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-productos.png)
  
* Operación CRUD: `OBTENER`. Tipo de request: `GET`. Endpoint: `{{URL}}/producto/{nombre}`.
  ![Prueba en Postman para Obtener Producto por Nombre](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-producto-nombre.png)
  
* Operación CRUD: `ACTUALIZAR`. Tipo de request: `PUT`. Endpoint: `{{URL}}/producto/{id}`.
  ![Prueba en Postman para Actualizar Producto por ID](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-actualizar-producto-id-1.png)

### Prueba para Proveedor
* Operación CRUD: `CREAR`. Tipo de request: `POST`. Endpoint: `{{URL}}/proveedor`.
  ![Prueba en Postman para Crear Proveedor](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-crear-proveedor.png)
  
* Operación CRUD: `LISTAR`. Tipo de request: `GET`. Endpoint: `{{URL}}/proveedor`.
  ![Prueba en Postman para Obtener Proveedores](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-proveedores.png)
  
* Operación CRUD: `OBTENER`. Tipo de request: `GET`. Endpoint: `{{URL}}/proveedor/{nombre}`.
  ![Prueba en Postman para Obtener Proveedor por Nombre](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-obtener-proveedor-nombre.png)
  
* Operación CRUD: `ACTUALIZAR`. Tipo de request: `PUT`. Endpoint: `{{URL}}/proveedor/{id}`.
  ![Prueba en Postman para Actualizar Proveedor por ID](https://raw.githubusercontent.com/Yellaber/backend-pedido/main/src/main/resources/static/images/postman-actualizar-proveedor-id-1.png)
