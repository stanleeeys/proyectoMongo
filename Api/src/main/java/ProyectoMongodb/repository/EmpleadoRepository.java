package ProyectoMongodb.repository;

import ProyectoMongodb.entity.Empleado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmpleadoRepository extends MongoRepository<Empleado, String> {
    List<Empleado> findByInformacionLaboralDepartamento(String departamento);
    List<Empleado> findByNombreCompletoApellidoPaterno(String apellido);
    Empleado findByCodigoEmpleado(String codigoEmpleado);
    Empleado findByInformacionContactoEmail(String email);
}
