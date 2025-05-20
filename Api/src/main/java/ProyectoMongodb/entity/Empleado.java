package ProyectoMongodb.entity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.CompoundIndex;

import java.util.List;

@Document(collection = "empleados")
@CompoundIndex(name = "departamento_puesto", def = "{\"informacionLaboral.departamento\": 1, \"informacionLaboral.puesto\": 1}")
public class Empleado {

    @Id

    private String id;
    @Indexed(unique = true)
    private String codigoEmpleado;
    private NombreCompleto nombreCompleto;
    private InformacionContacto informacionContacto;
    private InformacionLaboral informacionLaboral;
    private String estatus;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }
    
    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }
    
    public NombreCompleto getNombreCompleto() {
        return nombreCompleto;
    }
    
    public void setNombreCompleto(NombreCompleto nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    public InformacionContacto getInformacionContacto() {
        return informacionContacto;
    }
    
    public void setInformacionContacto(InformacionContacto informacionContacto) {
        this.informacionContacto = informacionContacto;
    }
    
    public InformacionLaboral getInformacionLaboral() {
        return informacionLaboral;
    }
    
    public void setInformacionLaboral(InformacionLaboral informacionLaboral) {
        this.informacionLaboral = informacionLaboral;
    }
    
    public String getEstatus() {
        return estatus;
    }
    
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Data
    public static class NombreCompleto {
        private String nombre;
        private String apellidoPaterno;
        private String apellidoMaterno;
    }

    @Data
    public static class InformacionContacto {
        private String email;
        private String telefono;
        private Direccion direccion;
    }

    @Data
    public static class Direccion {
        private String calle;
        private String colonia;
        private String ciudad;
        private String codigoPostal;
        private String pais;
    }

    @Data
    public static class InformacionLaboral {
        private String departamento;
        private String puesto;
        private String fechaContratacion;
        private String tipoContrato;
        private Double salarioBase;
        private String moneda;
        private String jornadaLaboral;
        private List<String> proyectosAsignados;
    }

}
