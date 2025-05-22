import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Repositorio {
	private Map<Integer, Video> videos = new HashMap<>();
	private Map<Integer, Gafanhoto> gafanhotos = new HashMap<>();

	public Video getVideos(int index) {
		/*
		 * O IF abaixo faz a verificação primeiro no cache
		 * Se não hover o objeto com o ID passado no "index" em cache
		 * Faz request no banco, retornando o objeto e também armazenando em cache
		 */
		if (videos.containsKey(index)) { // Verifica se existe um video com ID passado no "index"
			return videos.get(index); // Se a codição for verdadeira, pega o video pelo ID usando o "index"
		}
		Connection conn = ConexaoMySQL.getConection();
		if (conn == null) {
			throw new IllegalStateException("Não foi possível conectar ao banco!");
		}

		VideoDAO daoV = new VideoDAO(conn);
		Video v = daoV.pegarV(index);

		if (v != null) {
			videos.put(index, v);
		}
		return v;
	}

	public Gafanhoto getGafanhotos(int index) {
		/*
		 *O IF abaixo faz a verificação primeiro no cache
		 * Se não houver o objeto com o ID passado no "index" em cache
		 * Faz request no banco, retornando o objeto e também guardando em cache
		 */
		
		if (gafanhotos.containsKey(index)) { // Verifica se existe um gafanhoto com ID passado no "index"
			return gafanhotos.get(index); // Se a codição for verdadeira, pega o gafanhoto pelo ID usando o "index"
		}
		Connection conn = ConexaoMySQL.getConection();
		if (conn == null) {
			throw new IllegalStateException("Não foi possível conectar ao banco");
		}

		GafanhotoDAO daoG = new GafanhotoDAO(conn);
		Gafanhoto g = daoG.pegarG(index);

		if (g != null) {
			gafanhotos.put(index, g);
		}
		return g;
	}

	protected void saveGafanDB(Gafanhoto g) {
		Connection conn = ConexaoMySQL.getConection(); // Recebe a conexão com o DB
		if (conn == null) { //
			throw new IllegalStateException("Não foi possível se conectar ao banco!");
		}

		GafanhotoDAO daoG = new GafanhotoDAO(conn); // Instancia a classe DAO e passa a conexão
		daoG.salvarG(g);
		System.out.println(g.getNome() + " Foi salvo com sucesso!");

		try {
			conn.close(); // Tenta fechar o acesso ao banco
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void saveVidDB(Video v) {
		Connection conn = ConexaoMySQL.getConection(); // Recebe a conexão com o DB
		if (conn == null) {
			throw new IllegalStateException("Não foi possível conectar ao banco!");
		}

		VideoDAO daoV = new VideoDAO(conn);
		daoV.salvarV(v); // Chama o método e passa o 'Video' com parâmentro onde vai salvar os dados
		System.out.println(v.getTitulo() + " Foi salvo com sucesso!");
	}

	protected int totalGDB() {
		Connection conn = ConexaoMySQL.getConection();
		if (conn == null) {
			throw new IllegalStateException("Não foi possível conectar ao banco ");
		}

		GafanhotoDAO daoG = new GafanhotoDAO(conn);

		int totalDB = daoG.contarG();
		return totalDB;
	}

	protected int totalVDB() {
		Connection conn = ConexaoMySQL.getConection();
		if (conn == null) {
			throw new IllegalStateException("Não foi possível conectar ao banco");
		}

		VideoDAO daoV = new VideoDAO(conn);

		int totalDB = daoV.contarV(); // Chama o método e armazena o total de Gafanhotos do DB na variável totalDB
		return totalDB;
	}

	protected void requestGafan(int resp) {
		Connection conn = ConexaoMySQL.getConection();
		if (conn == null) {
			throw new IllegalStateException("Não foi possível conectar ao banco!");
		}

		GafanhotoDAO daoG = new GafanhotoDAO(conn);
		Gafanhoto g = daoG.pegarG(resp); // Passa o ID do gafanhato e recebe os dados do mesmo

		if (g != null) {
			gafanhotos.put(resp, g);
			System.out.println(gafanhotos.get(resp));

		}
	}

	protected void requestVideo(int resp) {
		Connection conn = ConexaoMySQL.getConection();
		if (conn == null) {
			throw new IllegalStateException("Não foi possível conectar ao banco!");
		}

		VideoDAO daoV = new VideoDAO(conn);
		Video v = daoV.pegarV(resp);

		if (v != null) {
			videos.put(resp, v);
			System.out.println(videos.get(resp));
		}
	}

	protected void updateGafan(int id) {
		Connection conn = ConexaoMySQL.getConection();
		if (conn == null) {
			throw new IllegalStateException("Não foi possível conectar ao banco");
		}

		GafanhotoDAO daoG = new GafanhotoDAO(conn);
		daoG.atualizarG(gafanhotos.get(id), id);
	}

	protected void updateVideo(int id) {
		Connection conn = ConexaoMySQL.getConection();
		if (conn == null) {
			throw new IllegalStateException("Não foi possível conectar ao banco!");
		}

		VideoDAO daoV = new VideoDAO(conn);
		daoV.atualizarV(videos.get(id), id);
	}
}
