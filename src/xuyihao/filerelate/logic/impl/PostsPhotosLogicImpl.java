package xuyihao.filerelate.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.filerelate.dao.PostsPhotosDao;
import xuyihao.filerelate.entity.PostsPhotos;
import xuyihao.filerelate.logic.PostsPhotosLogic;
import xuyihao.tools.utils.DateUtils;

@Component("PostsPhotosLogic")
public class PostsPhotosLogicImpl implements PostsPhotosLogic {
	@Autowired
	private PostsPhotosDao postsPhotosDao;

	public void setPostsPhotosDao(PostsPhotosDao postsPhotosDao) {
		this.postsPhotosDao = postsPhotosDao;
	}

	public boolean savePostsPhotos(String Post_ID, String HeadPhoto_ID, List<String> postPhotosList) {
		PostsPhotos postsPhotos = new PostsPhotos();
		postsPhotos.setPost_ID(Post_ID);
		postsPhotos.setHeadPhoto_ID(HeadPhoto_ID);
		if (postPhotosList != null && postPhotosList.size() > 0) {
			String idCombine = "";
			for (String id : postPhotosList) {
				idCombine += (id + "&&");
			}
			idCombine = idCombine.substring(0, idCombine.length() - 2);
			postsPhotos.setPhoto_ID_Combine(idCombine);
		}
		postsPhotos.setPostPhoto_addTime(DateUtils.currentDateTime());
		return this.postsPhotosDao.savePostsPhotos(postsPhotos);
	}

	public boolean deletePostsPhotos(String Post_ID) {
		return this.postsPhotosDao.deletePostsPhotos(Post_ID);
	}

	public boolean changePostsPhotosInfo(String Post_ID, String HeadPhoto_ID, List<String> postPhotosList) {
		PostsPhotos postsPhotos = this.postsPhotosDao.query(Post_ID);
		if (postsPhotos.get_id() == 0) {
			return false;
		}
		if (HeadPhoto_ID != null && !HeadPhoto_ID.equals("")) {
			postsPhotos.setHeadPhoto_ID(HeadPhoto_ID);
		}
		if (postPhotosList != null && postPhotosList.size() > 0) {
			String idCombine = "";
			for (String id : postPhotosList) {
				idCombine += (id + "&&");
			}
			idCombine = idCombine.substring(0, idCombine.length() - 2);
			postsPhotos.setPhoto_ID_Combine(idCombine);
		}
		return this.postsPhotosDao.updatePostsPhotos(postsPhotos);
	}

	public PostsPhotos getPostsPhotosInfo(String Post_ID) {
		return this.postsPhotosDao.query(Post_ID);
	}

	public String getPostsHeadPhoto(String Post_ID) {
		PostsPhotos postsPhotos = this.postsPhotosDao.query(Post_ID);
		return postsPhotos.getHeadPhoto_ID();
	}

	public List<String> getPostsOtherPhotos(String Post_ID) {
		PostsPhotos postsPhotos = this.postsPhotosDao.query(Post_ID);
		String[] idArray = postsPhotos.getPhoto_ID_Combine().split("&&");
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < idArray.length; i++) {
			idList.add(idArray[i]);
		}
		return idList;
	}
}