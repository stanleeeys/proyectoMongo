use empresaDB
db.empleados.insertOne({
  codigoEmpleado: "EMP-1001",
  nombreCompleto: {
    nombre: "Ana",
    apellidoPaterno: "García",
    apellidoMaterno: "López"
  },
  informacionContacto: {
    email: "ana.garcia@empresa.com",
    telefono: "+525512345678",
    direccion: {
      calle: "Av. Reforma 123",
      colonia: "Cuauhtémoc",
      ciudad: "Ciudad de México",
      codigoPostal: "06500",
      pais: "México"
    }
  },
  informacionLaboral: {
    departamento: "TI",
    puesto: "Desarrollador Senior",
    fechaContratacion: new Date("2020-05-15"),
    tipoContrato: "Tiempo completo",
    salarioBase: 55000,
    moneda: "MXN",
    jornadaLaboral: "L-V 9:00-18:00",
    proyectosAsignados: ["Sistema ERP", "App Móvil"]
  },
  estatus: "Activo"
})
// indices
// Índice predeterminado (_id) — este ya se crea automáticamente

// 1. Índice compuesto en departamento y puesto
db.empleados.createIndex(
  {
    "informacionLaboral.departamento": 1,
    "informacionLaboral.puesto": 1
  },
  {
    name: "departamento_puesto"
  }
);

// 2. Índice único en codigoEmpleado
db.empleados.createIndex(
  { "codigoEmpleado": 1 },
  {
    unique: true,
    name: "codigoEmpleado"
  }
);

// 3. Índice único en informacionContacto.email
db.empleados.createIndex(
  { "informacionContacto.email": 1 },
  {
    unique: true,
    name: "informacionContacto.email_1"
  }
);

//para buscar indix si se hicieron
db.empleados.getIndexes()

//para buscar 
ID
db.empleados.findOne({ _id: ObjectId("682aa5b90f980e9740f4a452") })

//por email y lo mismo los otros
db.empleados.find({ "informacionContacto.email": "juan.perez@empresa.com" })

// para buscar por informacion laboral
db.empleados.find({
  "informacionLaboral.departamento": "Ventas",
  "informacionLaboral.puesto": "Gerente"
});
