package com.xuyihao.filerelate.dao.impl;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.filerelate.dao.CoursesPhotosDao;
import com.xuyihao.filerelate.entity.CoursesPhotos;

public class CoursesPhotosDaoImpl implements CoursesPhotosDao {
	private DatabaseConnector databaseConnector = new DatabaseConnector();

	public boolean saveCoursesPhotos(CoursesPhotos coursesPhotos) {
		String sql = "insert into " + CoursesPhotos.BASE_TABLE_NAME + " values(null, '" + coursesPhotos.getCrs_ID() + "', '"
				+ coursesPhotos.getHeadPhoto_ID() + "', '" + coursesPhotos.getPhoto_ID_Combine() + "', '"
				+ coursesPhotos.getCrsPhoto_addTime() + "')";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteCoursesPhotos(String Crs_ID) {
		String sql = "delete from " + CoursesPhotos.BASE_TABLE_NAME + " where " + CoursesPhotos.BASE_COURSESPHOTOS_COURSE_ID
				+ " = '" + Crs_ID + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateCoursesPhotos(CoursesPhotos coursesPhotos) {
		return false;
	}

	public boolean updateCoursesPhotosBySql(String update) {
		return false;
	}

	public CoursesPhotos query(String Crs_ID) {
		return null;
	}

	public CoursesPhotos queryBySql(String query) {
		return null;
	}

	public void closeDBConnection() {
	}
}