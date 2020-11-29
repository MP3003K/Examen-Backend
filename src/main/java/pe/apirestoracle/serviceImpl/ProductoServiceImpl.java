package pe.apirestoracle.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.apirestoracle.dao.ProductoDao;
import pe.apirestoracle.entity.Producto;
import pe.apirestoracle.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{
@Autowired
private ProductoDao productoDao;
	@Override
	public Map<String, Object> read(int id) {
		// TODO Auto-generated method stub
		return productoDao.read(id);
	}

	@Override
	public List<Map<String, Object>> readAll() {
		// TODO Auto-generated method stub
		return productoDao.readAll();
	}

	@Override
	public int create(Producto r) {
		// TODO Auto-generated method stub
		return productoDao.create(r);
	}

	@Override
	public int update(Producto r) {
		// TODO Auto-generated method stub
		System.out.println("service: "+r.getNomprod());
		return productoDao.update(r);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return productoDao.delete(id);
	}

	@Override
	public int update(int id) {
		// TODO Auto-generated method stub
		return productoDao.update(id);
	}

}
