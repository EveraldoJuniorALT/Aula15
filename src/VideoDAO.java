import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VideoDAO {
	private Connection conn;

	public VideoDAO(Connection conn) {
		this.conn = conn;
	}

	protected void salvarV(Video v) {
		String sql = "INSERT INTO video (titulo, avaliacao, views, gostei, naogostei, reproduzindo) VALUES (?, ?, ?, ?, ?, ?)";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, v.getTitulo());
			stmt.setDouble(2, v.getAvaliacao());
			stmt.setInt(3, v.getViews());
			stmt.setInt(4, v.getGostei());
			stmt.setInt(5, v.getNaoGostei());
			stmt.setBoolean(6, v.getReproduzindo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected int contarV() {
		String sql = "SELECT COUNT(*) FROM video";
		int totalVDB = 0;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				totalVDB = rs.getInt(1);
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalVDB;
	}

	protected Video pegarV(int idVideo) {
		String sql = "SELECT * FROM video WHERE id = ?";
		Video videos = null;

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, idVideo);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String titulo = rs.getString("titulo");
				double avaliacao = rs.getDouble("avaliacao");
				int views = rs.getInt("views");
				int gostei = rs.getInt("gostei");
				int naogostei = rs.getInt("naogostei");
				boolean reproduzindo = rs.getBoolean("reproduzindo");

				videos = new Video(titulo);
				videos.setAvaliacao(avaliacao);
				videos.setViews(views);
				videos.setGostei(gostei);
				videos.setNaoGostei(naogostei);
				videos.setReproduzindo(reproduzindo);
			} else {
				System.out.println("Vídeo não encontrado!");
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videos;
	}

	protected void atualizarV(Video v, int idV) {
		String sql = "UPDATE video SET avaliacao = ?, views = ?, gostei = ?, naogostei = ? WHERE id = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setDouble(1, v.getAvaliacao());
			stmt.setInt(2, v.getViews());
			stmt.setInt(3, v.getGostei());
			stmt.setInt(4, v.getNaoGostei());
			stmt.setInt(5, idV);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
