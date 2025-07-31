db = db.getSiblingDB('reto-clientedb');

db.clientes.insertOne({
  codigoUnico: "ABC123456",
  nombres: "Jhon",
  apellidos: "Alvarado",
  tipoDocumento: "DNI",
  numeroDocumento: "12345678"
});

db = db.getSiblingDB('reto-productosdb');
db.productos.insertMany([
  {
    codigoUnico: "ABC123456",
    tipoProducto: "Cuenta de Ahorros",
    nombreProducto: "Cuenta Joven",
    saldo: 1500.5
  },
  {
    codigoUnico: "ABC123456",
    tipoProducto: "Tarjeta de Crédito",
    nombreProducto: "Visa Clásica",
    saldo: 2300
  }
]);
