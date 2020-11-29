package pe.apirestoracle.controller;

import java.util.List;
import java.util.Map;

import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.apirestoracle.entity.Producto;
import pe.apirestoracle.service.ProductoService;



@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/productos")
public class ProductoController {
@Autowired
private ProductoService productoService;

@GetMapping("/all")
public List<Map<String, Object>> readAll(){
	return productoService.readAll();
}
@GetMapping("/{id}")
public Map<String, Object> read(@PathVariable int id ) {
	try {
		 return productoService.read(id);
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("error");
		return null;
	}
}
@DeleteMapping("/delete/{id}")
public int delete(@PathVariable int id ) {
	return productoService.delete(id);
}
//PostMapping permite registrar un nuevo producto
@PostMapping("/add")
public int create(@RequestBody Producto r) {
	System.out.println("Crear: "+r.getNomprod());
	return productoService.create(r);
}
//PutMappin permite modificar producto
@PutMapping("/update/{id}")
public int edit(@RequestBody Producto  r, @PathVariable int id) {
	//Map<String, Object> map = rolService.read(id);
	System.out.println(r.getNomprod());
	Producto producto = new Producto();
	producto.setIdproducto(id);
  producto.setNomprod(r.getNomprod());
  producto.setPrecio(r.getPrecio());
  producto.setStock(r.getStock());
 	
	return productoService.update(producto);
}
@PutMapping("/update/logica/{id}")
public int edit(@PathVariable int id) {
	System.out.println(id);
	return productoService.update(id);
}
}
