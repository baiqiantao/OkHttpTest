package ok.bqt.com.okhttptest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 利用GsonFormat自动生成的model
 */
public class User {

	/**
	 * userown : {"vip":0,"car":0}
	 * viptime : 0
	 * allcar :
	 * allcar_time :
	 * result : 1
	 * msg : {"id":"11893722","sid":"11893722","is_liang":2,"openup_liang":2,"username":"103468","nickname":"我!","head_image":"http://image.95xiu.com/upload/head_image/2017-02-08/11893722-1486535406
	 * .png","user_type":"0","register_type":"4","wealth_integral":15330,"star_integral":0,"signature":"哈哈","phone":"","qq":"","sex":"1","session_id":"a5b0953b25918c320442b4d162d724c9","gold":"0",
	 * "free_gift":"9","is_binding":"0","tc_buy":"0","diamond":"0","tags":"","follow_list":"36049227,6880983,15174141","follow_num":"3","at_follow_num":"0","wealth_level":3,
	 * "wealth_next_integral":30000,"star_level":0,"star_next_integral":1000,"is_play":"0","firstPay":0,"news_count":0,"dvip_hidden":0,"giftPackage":"0,0,0,0","playremind_list":""}
	 * dynamic : {"count":"0","first":[]}
	 * albums : []
	 * anchor_info :
	 * family_info : {"id":20,"creator_id":10216853,"create_status":2,"family_name":"浅Se嫣家军","publish":"在帮会的人要做贡献，每天最基本的要求就是集合，希望大家都能集极一些，耽误大家几分钟就能保住帮会等级","level":3,"level_integral":481,
	 * "logo_image":"http://image.95xiu.com/upload//family_logo/2/20-5765650c6e739.jpg","user_count":"24","anchor_count":0,"create_time":"2015-09-23","badge_name":"嫣家","creator_nickname":"C x゛『英哥』",
	 * "member_count":24,"deputyleader_count":2,"ufType":0,"apply_status":"2"}
	 * backpack_gifts : []
	 * user_customizaton_gift : []
	 * user_badge : {"badge":"","badge_time":""}
	 * myvip_list : []
	 * isplay_count : 0
	 * nweibo_count : 398
	 * range_money_reward : [{"level":11,"1000":"2.00","2000":"2.50","5000":"3.50","10000":"5.00","50000":"7.00"},{"level":12,"1000":"2.00","2000":"2.50","5000":"3.50","10000":"5.00","50000":"7
	 * .00"},{"level":13,"1000":"2.00","2000":"2.50","5000":"3.50","10000":"5.00","50000":"7.00"},{"level":14,"1000":"2.00","2000":"2.50","5000":"3.50","10000":"5.00","50000":"7.00"},{"level":15,
	 * "1000":"2.00","2000":"2.50","5000":"3.50","10000":"5.00","50000":"7.00"},{"level":16,"1000":"2.25","2000":"3.00","5000":"4.25","10000":"6.00","50000":"8.00"},{"level":17,"1000":"2.25",
	 * "2000":"3.00","5000":"4.25","10000":"6.00","50000":"8.00"},{"level":18,"1000":"2.25","2000":"3.00","5000":"4.25","10000":"6.00","50000":"8.00"},{"level":19,"1000":"2.25","2000":"3.00",
	 * "5000":"4.25","10000":"6.00","50000":"8.00"},{"level":20,"1000":"2.25","2000":"3.00","5000":"4.25","10000":"6.00","50000":"8.00"},{"level":21,"1000":"2.50","2000":"3.50","5000":"5.00",
	 * "10000":"7.00","50000":"9.00"},{"level":22,"1000":"2.50","2000":"3.50","5000":"5.00","10000":"7.00","50000":"9.00"},{"level":23,"1000":"2.50","2000":"3.50","5000":"5.00","10000":"7.00",
	 * "50000":"9.00"},{"level":24,"1000":"2.75","2000":"4.00","5000":"5.75","10000":"8.00","50000":"10.00"},{"level":25,"1000":"2.75","2000":"4.00","5000":"5.75","10000":"8.00","50000":"10.00"},
	 * {"level":26,"1000":"2.75","2000":"4.00","5000":"5.75","10000":"8.00","50000":"10.00"},{"level":27,"1000":"2.75","2000":"4.00","5000":"5.75","10000":"8.00","50000":"10.00"}]
	 * other_pay_flag : 1
	 */

	private UserownBean userown;
	private int viptime;
	private String allcar;
	private String allcar_time;
	private String result;
	private MsgBean msg;
	private DynamicBean dynamic;
	private String anchor_info;
	private FamilyInfoBean family_info;
	private UserBadgeBean user_badge;
	private String isplay_count;
	private String nweibo_count;
	private int other_pay_flag;
	private List<?> albums;
	private List<?> backpack_gifts;
	private List<?> user_customizaton_gift;
	private List<?> myvip_list;
	private List<RangeMoneyRewardBean> range_money_reward;

	public UserownBean getUserown() {
		return userown;
	}

	public void setUserown(UserownBean userown) {
		this.userown = userown;
	}

	public int getViptime() {
		return viptime;
	}

	public void setViptime(int viptime) {
		this.viptime = viptime;
	}

	public String getAllcar() {
		return allcar;
	}

	public void setAllcar(String allcar) {
		this.allcar = allcar;
	}

	public String getAllcar_time() {
		return allcar_time;
	}

	public void setAllcar_time(String allcar_time) {
		this.allcar_time = allcar_time;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public MsgBean getMsg() {
		return msg;
	}

	public void setMsg(MsgBean msg) {
		this.msg = msg;
	}

	public DynamicBean getDynamic() {
		return dynamic;
	}

	public void setDynamic(DynamicBean dynamic) {
		this.dynamic = dynamic;
	}

	public String getAnchor_info() {
		return anchor_info;
	}

	public void setAnchor_info(String anchor_info) {
		this.anchor_info = anchor_info;
	}

	public FamilyInfoBean getFamily_info() {
		return family_info;
	}

	public void setFamily_info(FamilyInfoBean family_info) {
		this.family_info = family_info;
	}

	public UserBadgeBean getUser_badge() {
		return user_badge;
	}

	public void setUser_badge(UserBadgeBean user_badge) {
		this.user_badge = user_badge;
	}

	public String getIsplay_count() {
		return isplay_count;
	}

	public void setIsplay_count(String isplay_count) {
		this.isplay_count = isplay_count;
	}

	public String getNweibo_count() {
		return nweibo_count;
	}

	public void setNweibo_count(String nweibo_count) {
		this.nweibo_count = nweibo_count;
	}

	public int getOther_pay_flag() {
		return other_pay_flag;
	}

	public void setOther_pay_flag(int other_pay_flag) {
		this.other_pay_flag = other_pay_flag;
	}

	public List<?> getAlbums() {
		return albums;
	}

	public void setAlbums(List<?> albums) {
		this.albums = albums;
	}

	public List<?> getBackpack_gifts() {
		return backpack_gifts;
	}

	public void setBackpack_gifts(List<?> backpack_gifts) {
		this.backpack_gifts = backpack_gifts;
	}

	public List<?> getUser_customizaton_gift() {
		return user_customizaton_gift;
	}

	public void setUser_customizaton_gift(List<?> user_customizaton_gift) {
		this.user_customizaton_gift = user_customizaton_gift;
	}

	public List<?> getMyvip_list() {
		return myvip_list;
	}

	public void setMyvip_list(List<?> myvip_list) {
		this.myvip_list = myvip_list;
	}

	public List<RangeMoneyRewardBean> getRange_money_reward() {
		return range_money_reward;
	}

	public void setRange_money_reward(List<RangeMoneyRewardBean> range_money_reward) {
		this.range_money_reward = range_money_reward;
	}

	public static class UserownBean {
		/**
		 * vip : 0
		 * car : 0
		 */

		private int vip;
		private int car;

		public int getVip() {
			return vip;
		}

		public void setVip(int vip) {
			this.vip = vip;
		}

		public int getCar() {
			return car;
		}

		public void setCar(int car) {
			this.car = car;
		}
	}

	public static class MsgBean {
		/**
		 * id : 11893722
		 * sid : 11893722
		 * is_liang : 2
		 * openup_liang : 2
		 * username : 103468
		 * nickname : 我!
		 * head_image : http://image.95xiu.com/upload/head_image/2017-02-08/11893722-1486535406.png
		 * user_type : 0
		 * register_type : 4
		 * wealth_integral : 15330
		 * star_integral : 0
		 * signature : 哈哈
		 * phone :
		 * qq :
		 * sex : 1
		 * session_id : a5b0953b25918c320442b4d162d724c9
		 * gold : 0
		 * free_gift : 9
		 * is_binding : 0
		 * tc_buy : 0
		 * diamond : 0
		 * tags :
		 * follow_list : 36049227,6880983,15174141
		 * follow_num : 3
		 * at_follow_num : 0
		 * wealth_level : 3
		 * wealth_next_integral : 30000
		 * star_level : 0
		 * star_next_integral : 1000
		 * is_play : 0
		 * firstPay : 0
		 * news_count : 0
		 * dvip_hidden : 0
		 * giftPackage : 0,0,0,0
		 * playremind_list :
		 */

		private String id;
		private String sid;
		private int is_liang;
		private int openup_liang;
		private String username;
		private String nickname;
		private String head_image;
		private String user_type;
		private String register_type;
		private int wealth_integral;
		private int star_integral;
		private String signature;
		private String phone;
		private String qq;
		private String sex;
		private String session_id;
		private String gold;
		private String free_gift;
		private String is_binding;
		private String tc_buy;
		private String diamond;
		private String tags;
		private String follow_list;
		private String follow_num;
		private String at_follow_num;
		private int wealth_level;
		private int wealth_next_integral;
		private int star_level;
		private int star_next_integral;
		private String is_play;
		private int firstPay;
		private int news_count;
		private int dvip_hidden;
		private String giftPackage;
		private String playremind_list;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSid() {
			return sid;
		}

		public void setSid(String sid) {
			this.sid = sid;
		}

		public int getIs_liang() {
			return is_liang;
		}

		public void setIs_liang(int is_liang) {
			this.is_liang = is_liang;
		}

		public int getOpenup_liang() {
			return openup_liang;
		}

		public void setOpenup_liang(int openup_liang) {
			this.openup_liang = openup_liang;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getHead_image() {
			return head_image;
		}

		public void setHead_image(String head_image) {
			this.head_image = head_image;
		}

		public String getUser_type() {
			return user_type;
		}

		public void setUser_type(String user_type) {
			this.user_type = user_type;
		}

		public String getRegister_type() {
			return register_type;
		}

		public void setRegister_type(String register_type) {
			this.register_type = register_type;
		}

		public int getWealth_integral() {
			return wealth_integral;
		}

		public void setWealth_integral(int wealth_integral) {
			this.wealth_integral = wealth_integral;
		}

		public int getStar_integral() {
			return star_integral;
		}

		public void setStar_integral(int star_integral) {
			this.star_integral = star_integral;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getQq() {
			return qq;
		}

		public void setQq(String qq) {
			this.qq = qq;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getSession_id() {
			return session_id;
		}

		public void setSession_id(String session_id) {
			this.session_id = session_id;
		}

		public String getGold() {
			return gold;
		}

		public void setGold(String gold) {
			this.gold = gold;
		}

		public String getFree_gift() {
			return free_gift;
		}

		public void setFree_gift(String free_gift) {
			this.free_gift = free_gift;
		}

		public String getIs_binding() {
			return is_binding;
		}

		public void setIs_binding(String is_binding) {
			this.is_binding = is_binding;
		}

		public String getTc_buy() {
			return tc_buy;
		}

		public void setTc_buy(String tc_buy) {
			this.tc_buy = tc_buy;
		}

		public String getDiamond() {
			return diamond;
		}

		public void setDiamond(String diamond) {
			this.diamond = diamond;
		}

		public String getTags() {
			return tags;
		}

		public void setTags(String tags) {
			this.tags = tags;
		}

		public String getFollow_list() {
			return follow_list;
		}

		public void setFollow_list(String follow_list) {
			this.follow_list = follow_list;
		}

		public String getFollow_num() {
			return follow_num;
		}

		public void setFollow_num(String follow_num) {
			this.follow_num = follow_num;
		}

		public String getAt_follow_num() {
			return at_follow_num;
		}

		public void setAt_follow_num(String at_follow_num) {
			this.at_follow_num = at_follow_num;
		}

		public int getWealth_level() {
			return wealth_level;
		}

		public void setWealth_level(int wealth_level) {
			this.wealth_level = wealth_level;
		}

		public int getWealth_next_integral() {
			return wealth_next_integral;
		}

		public void setWealth_next_integral(int wealth_next_integral) {
			this.wealth_next_integral = wealth_next_integral;
		}

		public int getStar_level() {
			return star_level;
		}

		public void setStar_level(int star_level) {
			this.star_level = star_level;
		}

		public int getStar_next_integral() {
			return star_next_integral;
		}

		public void setStar_next_integral(int star_next_integral) {
			this.star_next_integral = star_next_integral;
		}

		public String getIs_play() {
			return is_play;
		}

		public void setIs_play(String is_play) {
			this.is_play = is_play;
		}

		public int getFirstPay() {
			return firstPay;
		}

		public void setFirstPay(int firstPay) {
			this.firstPay = firstPay;
		}

		public int getNews_count() {
			return news_count;
		}

		public void setNews_count(int news_count) {
			this.news_count = news_count;
		}

		public int getDvip_hidden() {
			return dvip_hidden;
		}

		public void setDvip_hidden(int dvip_hidden) {
			this.dvip_hidden = dvip_hidden;
		}

		public String getGiftPackage() {
			return giftPackage;
		}

		public void setGiftPackage(String giftPackage) {
			this.giftPackage = giftPackage;
		}

		public String getPlayremind_list() {
			return playremind_list;
		}

		public void setPlayremind_list(String playremind_list) {
			this.playremind_list = playremind_list;
		}
	}

	public static class DynamicBean {
		/**
		 * count : 0
		 * first : []
		 */

		private String count;
		private List<?> first;

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public List<?> getFirst() {
			return first;
		}

		public void setFirst(List<?> first) {
			this.first = first;
		}
	}

	public static class FamilyInfoBean {
		/**
		 * id : 20
		 * creator_id : 10216853
		 * create_status : 2
		 * family_name : 浅Se嫣家军
		 * publish : 在帮会的人要做贡献，每天最基本的要求就是集合，希望大家都能集极一些，耽误大家几分钟就能保住帮会等级
		 * level : 3
		 * level_integral : 481
		 * logo_image : http://image.95xiu.com/upload//family_logo/2/20-5765650c6e739.jpg
		 * user_count : 24
		 * anchor_count : 0
		 * create_time : 2015-09-23
		 * badge_name : 嫣家
		 * creator_nickname : C x゛『英哥』
		 * member_count : 24
		 * deputyleader_count : 2
		 * ufType : 0
		 * apply_status : 2
		 */

		private int id;
		private int creator_id;
		private int create_status;
		private String family_name;
		private String publish;
		private int level;
		private int level_integral;
		private String logo_image;
		private String user_count;
		private int anchor_count;
		private String create_time;
		private String badge_name;
		private String creator_nickname;
		private int member_count;
		private int deputyleader_count;
		private int ufType;
		private String apply_status;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getCreator_id() {
			return creator_id;
		}

		public void setCreator_id(int creator_id) {
			this.creator_id = creator_id;
		}

		public int getCreate_status() {
			return create_status;
		}

		public void setCreate_status(int create_status) {
			this.create_status = create_status;
		}

		public String getFamily_name() {
			return family_name;
		}

		public void setFamily_name(String family_name) {
			this.family_name = family_name;
		}

		public String getPublish() {
			return publish;
		}

		public void setPublish(String publish) {
			this.publish = publish;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getLevel_integral() {
			return level_integral;
		}

		public void setLevel_integral(int level_integral) {
			this.level_integral = level_integral;
		}

		public String getLogo_image() {
			return logo_image;
		}

		public void setLogo_image(String logo_image) {
			this.logo_image = logo_image;
		}

		public String getUser_count() {
			return user_count;
		}

		public void setUser_count(String user_count) {
			this.user_count = user_count;
		}

		public int getAnchor_count() {
			return anchor_count;
		}

		public void setAnchor_count(int anchor_count) {
			this.anchor_count = anchor_count;
		}

		public String getCreate_time() {
			return create_time;
		}

		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}

		public String getBadge_name() {
			return badge_name;
		}

		public void setBadge_name(String badge_name) {
			this.badge_name = badge_name;
		}

		public String getCreator_nickname() {
			return creator_nickname;
		}

		public void setCreator_nickname(String creator_nickname) {
			this.creator_nickname = creator_nickname;
		}

		public int getMember_count() {
			return member_count;
		}

		public void setMember_count(int member_count) {
			this.member_count = member_count;
		}

		public int getDeputyleader_count() {
			return deputyleader_count;
		}

		public void setDeputyleader_count(int deputyleader_count) {
			this.deputyleader_count = deputyleader_count;
		}

		public int getUfType() {
			return ufType;
		}

		public void setUfType(int ufType) {
			this.ufType = ufType;
		}

		public String getApply_status() {
			return apply_status;
		}

		public void setApply_status(String apply_status) {
			this.apply_status = apply_status;
		}
	}

	public static class UserBadgeBean {
		/**
		 * badge :
		 * badge_time :
		 */

		private String badge;
		private String badge_time;

		public String getBadge() {
			return badge;
		}

		public void setBadge(String badge) {
			this.badge = badge;
		}

		public String getBadge_time() {
			return badge_time;
		}

		public void setBadge_time(String badge_time) {
			this.badge_time = badge_time;
		}
	}

	public static class RangeMoneyRewardBean {
		/**
		 * level : 11
		 * 1000 : 2.00
		 * 2000 : 2.50
		 * 5000 : 3.50
		 * 10000 : 5.00
		 * 50000 : 7.00
		 */

		private int level;
		@SerializedName("1000") private String _$1000;
		@SerializedName("2000") private String _$2000;
		@SerializedName("5000") private String _$5000;
		@SerializedName("10000") private String _$10000;
		@SerializedName("50000") private String _$50000;

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public String get_$1000() {
			return _$1000;
		}

		public void set_$1000(String _$1000) {
			this._$1000 = _$1000;
		}

		public String get_$2000() {
			return _$2000;
		}

		public void set_$2000(String _$2000) {
			this._$2000 = _$2000;
		}

		public String get_$5000() {
			return _$5000;
		}

		public void set_$5000(String _$5000) {
			this._$5000 = _$5000;
		}

		public String get_$10000() {
			return _$10000;
		}

		public void set_$10000(String _$10000) {
			this._$10000 = _$10000;
		}

		public String get_$50000() {
			return _$50000;
		}

		public void set_$50000(String _$50000) {
			this._$50000 = _$50000;
		}
	}
}