package ProyectoMongodb.controller;

import ProyectoMongodb.entity.Empleado;
import ProyectoMongodb.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoRepository empleadoRepository;

    // CREATE
    @PostMapping
    public Empleado crearEmpleado(@RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }

    // READ ALL
    @GetMapping
    public List<Empleado> obtenerTodosEmpleados() {
        return empleadoRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable String id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);
        return empleado.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // READ BY DEPARTMENT
    @GetMapping("/departamento/{departamento}")
    public List<Empleado> obtenerEmpleadosPorDepartamento(@PathVariable String departamento) {
        return empleadoRepository.findByInformacionLaboralDepartamento(departamento);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable String id, @RequestBody Empleado empleadoActualizado) {
        return empleadoRepository.findById(id)
                .map(empleadoExistente -> {
                    // Mantener el ID original y actualizar los demás campos
                    empleadoActualizado.setId(id);
                    return ResponseEntity.ok(empleadoRepository.save(empleadoActualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // FIND  FOR CÓDIGO THE JOB
    @GetMapping("/codigo/{codigoEmpleado}")
    public ResponseEntity<Empleado> buscarPorCodigoEmpleado(@PathVariable String codigoEmpleado) {
        try {
            Empleado empleado = empleadoRepository.findByCodigoEmpleado(codigoEmpleado);
            if (empleado != null) {
                return ResponseEntity.ok(empleado);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado con código: " + codigoEmpleado);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar empleado", e);
        }
    }

    // FIND FOR EMAIL
    @GetMapping("/email/{email}")
    public ResponseEntity<Empleado> buscarPorEmail(@PathVariable String email) {
        try {
            Empleado empleado = empleadoRepository.findByInformacionContactoEmail(email);
            if (empleado != null) {
                return ResponseEntity.ok(empleado);
            }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado con email: " + email);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar empleado", e);
        }
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarEmpleado(@PathVariable String id) {
        try {
            return empleadoRepository.findById(id)
                    .map(empleado -> {
                        empleadoRepository.delete(empleado);
                        return ResponseEntity.ok().build();
                    })
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado con ID: " + id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar empleado", e);
        }
    }
}
