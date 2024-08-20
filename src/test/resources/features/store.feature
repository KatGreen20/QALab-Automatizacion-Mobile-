Feature: Validar la funcionalidad del carrito de compras
  @Mydemoapp
  Scenario Outline: Compra en tienda online
    Given estoy en la aplicación de SauceLabs
    And valido que carguen correctamente los productos en la galeria
    When agrego <UNIDADES> del siguiente producto "<PRODUCTO>"
    Then valido que el carrito de compra actualice correctamente "<PRODUCTO>" con <UNIDADES> unidades
    Examples:
      | PRODUCTO                  | UNIDADES |
      | Sauce Labs Backpack       | 1        |
      | Sauce Labs Bolt - T-Shirt | 1        |
      | Sauce Labs Bike Light     | 2        |
