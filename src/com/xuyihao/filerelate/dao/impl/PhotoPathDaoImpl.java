package com.xuyihao.filerelate.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.filerelate.dao.PhotoPathDao;
import com.xuyihao.filerelate.entity.PhotoPath;

public class PhotoPathDaoImpl implements PhotoPathDao {
	private DatabaseConnector databaseConnector = new DatabaseConnector();

	public boolean savePhotoPath(PhotoPath photoPath) {
		boolean flag = true;
		String sql = "insert into PhotoPath values(null, '" + photoPath.getPhoto_ID() + "', '"
				+ photoPath.getPhoto_pathName() + "', '" + photoPath.getThumbnail_pathName() + "', '"
				+ photoPath.getThumbnail_pathName() + "')";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean deletePhotoPath(String Photo_ID) {
		boolean flag = true;
		String sql = "delete from PhotoPath where Photo_ID = '" + Photo_ID + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean updatePhotoPath(PhotoPath photoPath) {
		String sql = "update PhotoPath set Photo_pathName = '" + photoPath.getPhoto_pathName()
				+ "', set Thumbnail_pathName = '" + photoPath.getThumbnail_pathName() + "' where Photo_ID = '"
				+ photoPath.getPhoto_ID() + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updatePhotoPathBySql(String update) {
		if (this.databaseConnector.executeUpdate(update) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public PhotoPath queryById(String Photo_ID) {
		String sql = "select * from PhotoPath where Photo_ID = '" + Photo_ID + "'";
		ResultSet rs = this.databaseConnector.executeQuery(sql);
		return this.getPhotoPathFromResultSet(rs);
	}

	public PhotoPath queryBySql(String query) {
		ResultSet rs = this.databaseConnector.executeQuery(query);
		return this.getPhotoPathFromResultSet(rs);
	}

	private PhotoPath getPhotoPathFromResultSet(ResultSet resultSet) {
		PhotoPath photoPath = new PhotoPath();
		try {
			if (resultSet.next()) {
				photoPath.set_id(resultSet.getLong(PhotoPath.BASE_PHOTOPATH_PHYSICAL_ID));
				photoPath.setPhoto_ID(resultSet.getString(PhotoPath.BASE_PHOTOPATH_ID));
				photoPath.setPhoto_pathName(resultSet.getString(PhotoPath.BASE_PHOTOPATH_PATHNAME));
				photoPath.setThumbnail_pathName(resultSet.getString(PhotoPath.BASE_PHOTOPATH_THUMBNAIL_PATHNAME));
				photoPath.setPhoto_addTime(resultSet.getString(PhotoPath.BASE_PHOTOPATH_ADDTIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photoPath;
	}

	public void closeDBConnection() {
		this.databaseConnector.close();
	}
}