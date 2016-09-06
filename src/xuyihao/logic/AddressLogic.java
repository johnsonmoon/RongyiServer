package xuyihao.logic;

import xuyihao.entity.Address;

/**
 * 地址逻辑
 *
 * Created by Xuyh on 2016/7/20.
 */
public interface AddressLogic {
	/**
	 * insert information into database table
	 *
	 * @param address instance of Address
	 * @return Add_ID if successfully
	 */
	public String saveAddress(Address address);

	/**
	 * get information from database table
	 *
	 * @param Add_ID
	 * @return true if successfully
	 */
	public Address getAddressInfo(String Add_ID);

	/**
	 * change information into the database table
	 *
	 * @param address instance of Address
	 * @return true if successfully
	 */
	public boolean changeAddressInfo(Address address);

	/**
	 * delete information from database table
	 *
	 * @param address instance of Address
	 * @return true if successfully
	 */
	public boolean deleteAddress(String Add_ID);
}
