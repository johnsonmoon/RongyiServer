package xuyihao.filerelate.dao;

import xuyihao.filerelate.entity.PhotoPath;

/**
 * 
 * @author Xuyh at 2016年8月29日 下午11:12:48.
 *
 */
public interface PhotoPathDao {
	/**
	 * 添加图片信息
	 * 
	 * @param photoPath
	 * @return
	 */
	public boolean savePhotoPath(PhotoPath photoPath);

	/**
	 * 删除图片信息
	 * 
	 * @param Photo_ID
	 * @return
	 */
	public boolean deletePhotoPath(String Photo_ID);

	/**
	 * 更新图片信息
	 * 
	 * @param photoPath
	 * @return
	 */
	public boolean updatePhotoPath(PhotoPath photoPath);

	/**
	 * 更新图片信息
	 * 
	 * @param update
	 * @return
	 */
	public boolean updatePhotoPathBySql(String update);

	/**
	 * 通过ID查找
	 * 
	 * @param Photo_ID
	 * @return
	 */
	public PhotoPath queryById(String Photo_ID);

	/**
	 * 查找
	 * 
	 * @param query
	 * @return
	 */
	public PhotoPath queryBySql(String query);

	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}