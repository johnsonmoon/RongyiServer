package xuyihao.filerelate.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import xuyihao.common.DatabaseConnector;
import xuyihao.filerelate.dao.PostsPhotosDao;
import xuyihao.filerelate.entity.PostsPhotos;

/**
 * 
 * @author Xuyh at 2016年9月2日 下午10:23:42.
 *
 */
@Component("PostsPhotosDao")
public class PostsPhotosDaoImpl implements PostsPhotosDao {
	private DatabaseConnector databaseConnector = new DatabaseConnector();

	public boolean savePostsPhotos(PostsPhotos postsPhotos) {
		String sql = "insert into " + PostsPhotos.BASE_TABLE_NAME + " values(null, '" + postsPhotos.getPost_ID()
				+ "', '" + postsPhotos.getHeadPhoto_ID() + "', '" + postsPhotos.getPhoto_ID_Combine() + "', '"
				+ postsPhotos.getPostPhoto_addTime() + "')";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deletePostsPhotos(String Post_ID) {
		String sql = "delete from " + PostsPhotos.BASE_TABLE_NAME + " where " + PostsPhotos.BASE_POSTSPHOTOS_POST_ID
				+ " = '" + Post_ID + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updatePostsPhotos(PostsPhotos postsPhotos) {
		String sql = "update " + PostsPhotos.BASE_TABLE_NAME + " set " + PostsPhotos.BASE_POSTSPHOTOS_HEADPHOTO_ID + " = '"
				+ postsPhotos.getHeadPhoto_ID() + "', " + PostsPhotos.BASE_POSTSPHOTOS_PHOTO_ID_COMBINE + " = '"
				+ postsPhotos.getPhoto_ID_Combine() + "' where " + PostsPhotos.BASE_POSTSPHOTOS_POST_ID + " = '"
				+ postsPhotos.getPost_ID() + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updatePostsPhotosBySql(String update) {
		if (this.databaseConnector.executeUpdate(update) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public PostsPhotos query(String Post_ID) {
		String sql = "select * from " + PostsPhotos.BASE_TABLE_NAME + " where " + PostsPhotos.BASE_POSTSPHOTOS_POST_ID
				+ " = '" + Post_ID + "'";
		ResultSet rs = this.databaseConnector.executeQuery(sql);
		return this.getPostsPhotoFromResultSet(rs);
	}

	public PostsPhotos queryBySql(String query) {
		ResultSet rs = this.databaseConnector.executeQuery(query);
		return this.getPostsPhotoFromResultSet(rs);
	}

	private PostsPhotos getPostsPhotoFromResultSet(ResultSet resultSet) {
		PostsPhotos postsPhotos = new PostsPhotos();
		try {
			if (resultSet.next()) {
				postsPhotos.set_id(resultSet.getLong(PostsPhotos.BASE_PHYSICAL_ID));
				postsPhotos.setHeadPhoto_ID(resultSet.getString(PostsPhotos.BASE_POSTSPHOTOS_HEADPHOTO_ID));
				postsPhotos.setPhoto_ID_Combine(resultSet.getString(PostsPhotos.BASE_POSTSPHOTOS_PHOTO_ID_COMBINE));
				postsPhotos.setPost_ID(resultSet.getString(PostsPhotos.BASE_POSTSPHOTOS_POST_ID));
				postsPhotos.setPostPhoto_addTime(resultSet.getString(PostsPhotos.BASE_POSTSPHOTOS_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return postsPhotos;
	}

	public void closeDBConnection() {
		this.databaseConnector.close();
	}
}