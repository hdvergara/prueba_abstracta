Feature: Gestión del Carrito de Compras
  Como usuario de la tienda online,
  Quiero buscar un producto, agregarlo y removerlo del carrito,
  Para validar que el proceso de compra funcione correctamente.

  Scenario: Agregar y remover iPhone del carrito
    Given El usuario se encuentra en la página principal
    When Ingresa "iPhone" en la barra de búsqueda y presiona buscar
    And Agrega al carrito la primera opcion de la busqueda
    And Hace clic en el botón del carrito de compras
    And Presiona View Cart
    Then Se valida que el iPhone se encuentre en el carrito de compras
    When Remueve el iPhone del carrito de compras
    Then Se valida que el iPhone ya no se encuentre en el carrito de compras
