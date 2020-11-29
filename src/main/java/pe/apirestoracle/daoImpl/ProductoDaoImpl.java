package pe.apirestoracle.daoImpl;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import pe.apirestoracle.dao.ProductoDao;
import pe.apirestoracle.entity.Producto;
import pe.apirestoracle.entity.Usuario;
@Component
public class ProductoDaoImpl implements ProductoDao {
@Autowired
private JdbcTemplate jdbcTemplate;
private SimpleJdbcCall simpleJdbcCall;
Gson gson =new Gson();
@Override
public Map<String, Object> read(int id) {
	// TODO Auto-generated method stub
	System.out.println(id);
	simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("sp_read_p").withCatalogName("PK_P")
													 .declareParameters(new SqlOutParameter("cursor_p", OracleTypes.CURSOR,
													  new ColumnMapRowMapper()), new SqlParameter("idproducto", Types.INTEGER));
	SqlParameterSource in = new MapSqlParameterSource().addValue("idproducto", id);
	return simpleJdbcCall.execute(in);
}

@Override
public List<Map<String, Object>> readAll() {
	List<Map<String,Object>> lista = new ArrayList<>();
	simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
			.withCatalogName("pk_p") //nombre del paquete
			.withProcedureName("SP_LIS_PRODUCTO") //nombre del procedimiento
			.declareParameters(new SqlOutParameter("cursor_p", OracleTypes.REF_CURSOR, new ColumnMapRowMapper()));	
			Map<String, Object> map = simpleJdbcCall.execute();
			lista.add(map);
	return lista;
}

@Override
public int create(Producto r) {
	// TODO Auto-generated method stub
	return  jdbcTemplate.update("call pk_p.sp_create_p(?,?,?)", r.getNomprod(),r.getPrecio(),r.getStock());
}

@Override
public int update(Producto r) {
	// TODO Auto-generated method stub
	return  jdbcTemplate.update("call pk_p.sp_update_p(?,?,?,?)", r.getIdproducto(),r.getNomprod(),r.getPrecio(),r.getStock());
}

@Override
public int delete(int id) {
	// TODO Auto-generated method stub
	return jdbcTemplate.update("call pk_p.sp_delete_p(?)", id);
}
@Override
public void convertitMap(Map<String, Object> map) {
	Gson rr = new Gson();
		Object lista =rr.toJson(map.get("cursor_p"));
	System.out.println(lista.toString());
	
}

@Override
public int update(int id) {
	// TODO Auto-generated method stub
	return  jdbcTemplate.update("call pk_p.sp_delete_logica_p(?)", id);
}


}
