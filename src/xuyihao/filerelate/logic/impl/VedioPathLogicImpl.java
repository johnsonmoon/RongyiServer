package xuyihao.filerelate.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.filerelate.dao.VedioPathDao;
import xuyihao.filerelate.entity.VedioPath;
import xuyihao.filerelate.logic.VedioPathLogic;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

@Component("VedioPathLogic")
public class VedioPathLogicImpl implements VedioPathLogic {
	@Autowired
	private VedioPathDao vedioPathDao;

	public void setVedioPathDao(VedioPathDao vedioPathDao) {
		this.vedioPathDao = vedioPathDao;
	}

	public String saveVedioPath(String Vedio_pathName, String Thumbnail_pathName) {
		VedioPath vedioPath = new VedioPath();
		String Vedio_ID = RandomUtils.getRandomString(15) + "Vedio";
		vedioPath.setVedio_ID(Vedio_ID);
		vedioPath.setVedio_addTime(DateUtils.currentDateTime());
		vedioPath.setVedio_pathName(Vedio_pathName);
		vedioPath.setThumbnail_pathName(Thumbnail_pathName);
		boolean flag = this.vedioPathDao.saveVedioPath(vedioPath);
		if (flag) {
			return Vedio_ID;
		} else {
			return "";
		}
	}

	public boolean deleteVedioPath(String Vedio_ID) {
		return this.vedioPathDao.deleteVedioPath(Vedio_ID);
	}

	public boolean changeVedioPathInfo(String Vedio_ID, String Vedio_pathName, String Thumbnail_pathName) {
		VedioPath vedioPath = this.vedioPathDao.queryById(Vedio_ID);
		if (vedioPath.get_id() == 0) {
			return false;
		}
		if (Vedio_pathName != null && !Vedio_pathName.equals("")) {
			vedioPath.setVedio_pathName(Vedio_pathName);
		}
		if (Thumbnail_pathName != null && !Thumbnail_pathName.equals("")) {
			vedioPath.setThumbnail_pathName(Thumbnail_pathName);
		}
		return this.vedioPathDao.updateVedioPath(vedioPath);
	}

	public VedioPath getVedioPathInfo(String Vedio_ID) {
		return this.vedioPathDao.queryById(Vedio_ID);
	}
}