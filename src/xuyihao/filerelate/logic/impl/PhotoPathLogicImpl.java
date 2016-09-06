package xuyihao.filerelate.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.filerelate.dao.PhotoPathDao;
import xuyihao.filerelate.entity.PhotoPath;
import xuyihao.filerelate.logic.PhotoPathLogic;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

@Component("PhotoPathLogic")
public class PhotoPathLogicImpl implements PhotoPathLogic {
	@Autowired
	private PhotoPathDao photoPathDao;

	public void setPhotoPathDao(PhotoPathDao photoPathDao) {
		this.photoPathDao = photoPathDao;
	}

	public String savePhotoPath(String Photo_pathName, String Thumbnail_pathName) {
		PhotoPath photoPath = new PhotoPath();
		String Photo_ID = RandomUtils.getRandomString(15) + "Photo";
		photoPath.setPhoto_ID(Photo_ID);
		photoPath.setPhoto_addTime(DateUtils.currentDateTime());
		photoPath.setPhoto_pathName(Photo_pathName);
		photoPath.setThumbnail_pathName(Thumbnail_pathName);
		boolean flag = this.photoPathDao.savePhotoPath(photoPath);
		if (flag) {
			return Photo_ID;
		} else {
			return "";
		}
	}

	public boolean deletePhotoPath(String Photo_ID) {
		return this.photoPathDao.deletePhotoPath(Photo_ID);
	}

	public boolean changePhotoPathInfo(String Photo_ID, String Photo_pathName, String Thumbnail_pathName) {
		PhotoPath photoPath = this.photoPathDao.queryById(Photo_ID);
		if (photoPath.get_id() == 0) {
			return false;
		}
		if (Photo_pathName != null && !Photo_pathName.equals("")) {
			photoPath.setPhoto_pathName(Photo_pathName);
		}
		if (Thumbnail_pathName != null && !Thumbnail_pathName.equals("")) {
			photoPath.setThumbnail_pathName(Thumbnail_pathName);
		}
		return this.photoPathDao.updatePhotoPath(photoPath);
	}

	public PhotoPath getPhotoPathInfo(String Photo_ID) {
		return this.photoPathDao.queryById(Photo_ID);
	}
}