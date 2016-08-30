package com.xuyihao.filerelate.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.filerelate.dao.VedioPathDao;
import com.xuyihao.filerelate.entity.VedioPath;

/**
 * 
 * @author Xuyh at 2016年8月30日 上午11:18:44.
 *
 */
public class VedioPathDaoImpl implements VedioPathDao {
	private DatabaseConnector databaseConnector = new DatabaseConnector();

	public boolean saveVedioPath(VedioPath vedioPath) {
		String sql = "insert into " + VedioPath.BASE_TABLE_NAME + " values(null, '" + vedioPath.getVedio_ID() + "', '"
				+ vedioPath.getVedio_pathName() + "', '" + vedioPath.getThumbnail_pathName() + "', '"
				+ vedioPath.getVedio_addTime() + "')";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteVedioPath(String Vedio_ID) {
		String sql = "delete from " + VedioPath.BASE_TABLE_NAME + " where " + VedioPath.BASE_VEDIOPATH_ID + " = '"
				+ Vedio_ID + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateVedioPath(VedioPath vedioPath) {
		String sql = "update " + VedioPath.BASE_TABLE_NAME + " set " + VedioPath.BASE_VEDIOPATH_PATHNAME + " = '"
				+ vedioPath.getVedio_pathName()
				+ "', " + VedioPath.BASE_VEDIOPATH_THUMBNAIL_PATHNAME + " = '" + vedioPath.getThumbnail_pathName() + "' where "
				+ VedioPath.BASE_VEDIOPATH_ID + " = '" + vedioPath.getVedio_ID() + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateVedioPath(String update) {
		if (this.databaseConnector.executeUpdate(update) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public VedioPath queryById(String Vedio_ID) {
		String sql = "select * from " + VedioPath.BASE_TABLE_NAME + " where " + VedioPath.BASE_VEDIOPATH_ID + " = '"
				+ Vedio_ID + "'";
		ResultSet rs = this.databaseConnector.executeQuery(sql);
		return this.getVedioPathByResultSet(rs);
	}

	public VedioPath QueryBySql(String query) {
		ResultSet rs = this.databaseConnector.executeQuery(query);
		return this.getVedioPathByResultSet(rs);
	}

	private VedioPath getVedioPathByResultSet(ResultSet resultSet) {
		VedioPath vedioPath = new VedioPath();
		try {
			if (resultSet.next()) {
				vedioPath.set_id(resultSet.getLong(VedioPath.BASE_VEDIOPATH_PHYSICAL_ID));
				vedioPath.setVedio_ID(resultSet.getString(VedioPath.BASE_VEDIOPATH_ID));
				vedioPath.setVedio_pathName(resultSet.getString(VedioPath.BASE_VEDIOPATH_PATHNAME));
				vedioPath.setThumbnail_pathName(resultSet.getString(VedioPath.BASE_VEDIOPATH_THUMBNAIL_PATHNAME));
				vedioPath.setVedio_addTime(resultSet.getString(VedioPath.BASE_VEDIOPATH_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vedioPath;
	}

	public void closeDBConnection() {
		this.databaseConnector.close();
	}
}