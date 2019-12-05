package com.fulan.mydemo.hjs.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

public class Utils {
	
	public static String createUser = "api";
	
//	public static String getHeadScolpture(){
//		return H5Utils.headSculpture.get(random(1,9));
//	}
	
	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("x-forwarded-for");
		if(ip != null&&ip.contains(",")){
			ip=ip.split(",")[0];
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static boolean regFind(String reg, String value) throws Exception {
		try {
			return Pattern.compile(reg).matcher(value).find();
		} catch (PatternSyntaxException e) {
			throw new Exception("正则有误。" + reg, e);
		} catch (IllegalStateException e) {
			throw new Exception("正则有误。" + reg, e);
		}
	}

	/**
	 * 验证对象是否为 enmty
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> boolean isEmpty(T t) {
		if (null == t) {
			return true;
		}

		// t extends Integer
		if (t instanceof Number && ((Number)t).doubleValue() == 0) {
			return true;
		}

		// t extends CharSequence
		if (t instanceof CharSequence && "".equals(t.toString().trim())) {
			return true;
		}

		// t extends Collection
		if (t instanceof Collection && ((Collection<?>)t).isEmpty()) {
			return true;
		}
		// Map
		if (t instanceof Map && ((Map<?, ?>) t).isEmpty()) {
			return true;
		}
		return false;
	}


	/**
	 * 集合转换
	 * @param list
	 * @param callable
	 * @return
	 */
//	public static <I,O> List<O> converList(List<I> list,IOCallable<I,O> callable){
//		if(isEmpty(list) || callable == null){
//			return new ArrayList<O>();
//		}
//		List<O> outList = new ArrayList<O>(); 
//		for (I i : list) {
//			O o = callable.exec(i);
//			if(Utils.isEmpty(o)){
//				continue;
//			}
//			outList.add(o);
//		}
//		return outList;
//	}

	/**
	 * 字符串截取
	 * @param value
	 * @param size 截取长度
	 * @return
	 */
	public static String substring(String value, int size) {
		if(isEmpty(value)){
			return "";
		}
		if(value.length() <= size){
			return value;
		}
		return value.substring(0, size);
	}

	/**
	 * 计算当前时间想距目标时间 与 有效时间的差额
	 * @param target 目标时间
	 * @param validTime 有效时间，单位分钟
	 * @return 单位秒
	 */
	public static long surplusTime(Date target,int validTime){
		long targetLong = target.getTime();
		long nowLong = new Date().getTime();
		long surplusLong = nowLong - targetLong;
		if(surplusLong <= 0){
			return 0;
		}

		long validLong = validTime * 60 * 1000;

		return (validLong - surplusLong) / 1000;
	}


//	public static String encoder64(String value){
//		if(null == value){ value = "";}
//
//		return new BASE64Encoder().encode(value.getBytes());
//	}
//
//	public static String decoder64(String value){
//		try {
//			byte[] bytes = new BASE64Decoder().decodeBuffer(value);
//			return new String(bytes,"UTF-8");
//		} catch (IOException ex) {
//			throw new ConvertException("BASE64 IOException",ex);
//		}
//	}


	/**
	 * 校验数据 是否全部为空
	 * </br>描述：只要有一个数据为空 => false，否则 => true
	 * @param data
	 * @return
	 */
	public static boolean allEmpty(Map<String, Object> data){
		if(isEmpty(data)){
			return true;
		}
		for (Map.Entry<String, Object> entry : data.entrySet()) {
			if(isEmpty(entry) == true){
				// 只要有一个不为 Empty ,则表示不全为 空
				return false;
			}
		}
		return true;
	}

	/**
	 * 校验数据 是否全部为空
	 * </br>描述：只要有一个数据为空 => false，否则 => true
	 * @param data
	 * @return
	 */
	public static boolean allEmpty(Object ... list){
		if(null == list || list.length <= 0){
			return true;
		}
		for (Object item : list) {
			if(isEmpty(item) == true){
				// 只要有一个不为 Empty ,则表示不全为 空
				return false;
			}
		}
		return true;
	}

	/**
	 * 创建一个 2010年之后的时间戳
	 * @return
	 */
	public static String createTimestamp(){
		Calendar calendar=Calendar.getInstance();
		calendar.set(2010,1,1,0,0,0);
		long time_2010 = calendar.getTime().getTime();

		long time_now = new Date().getTime();

		long time_second = (time_now - time_2010)/1000; 

		return String.valueOf(time_second);
	}

	/**
	 * 生成单号
	 * @param value 客户编号
	 * @return
	 */
	public static String createNumber(int value){
		if(Utils.isEmpty(value)){
			return createTimestamp() + random(5);
		}else{
			return createTimestamp() + String.format("%05d", value);
		}
	}

	/**
	 * 生成主订单（一秒只能生成一个）
	 * @param value 客户编号
	 * @return
	 */
	public static String createOrderNumber(String prefix,int value){
		if(Utils.isEmpty(value)){
			return prefix+createTimestamp() + random(5);
		}else{
			return prefix+createTimestamp() + String.format("%05d", value);
		}
	}


	/**
	 *  生成子订单ID
	 * 
	 * @date 2016年2月24日 下午5:57:16
	 * @return
	 */
	public static String createChildOrderId(String prefix){
		return prefix+createTimestamp() + random(5);
	}

	/**
	 * 生成随机数
	 * @param length 位数 ，默认4位
	 * @return
	 */
	public static String random(int length){
		if(length == 0){ length = 4;}
		Number max = Math.pow(10, length);
		int random = new Random(System.nanoTime()).nextInt(max.intValue());
		return String.format(MessageFormat.format("%0{0}d", length), random);
	}

	/**
	 * 获取 UUID
	 * @return
	 */
	public static String UUID(){
		String uuid = UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}

	/**
	 * 生成邀请码
	 * @return
	 */
	public static String inviteCode(int n){
		final char[] keleyi = "123456789ABCDEFGHIJKLMNPQRSTUVWXYZ".toCharArray();
		String UUID = UUID();
		char[] ba = UUID.toCharArray();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < n; i++) {
			sb.append(keleyi[((ba[i] + ba[n + i])% 33)]);
		}
		return sb.toString();
	}
	/**
	 * 地址集合
	 */
	public static List<String> addressList;

	/**
	 * 
	 * <p class="detail"> 随机生成名字 </p>
	 * @author <a href="mailto:niukun@taojinzi.com ">牛坤</a> 2015年9月21日 上午9:40:22
	 * @return
	 */
	public static String getNickname() {
		return "wx"+random(4);
	}

	/**
	 * 
	 * <p class="detail"> 生成随机数 </p>
	 * @author <a href="mailto:niukun@taojinzi.com ">牛坤</a> 2015年9月16日 下午11:06:11
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min,int max) {
		return (int)(min+Math.random()*(max-min+1));
	}
	
//	public static double random(double min,double max,Integer decimal){
//		return UtilMoney.roundToDouble(min+Math.random()*(max-min), decimal);
//	}

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 *             如果实例化 JavaBean 失败
	 * @throws Exception 
	 */
	public static Map<String, Object> convertBean(Object bean) throws Exception {
		if(bean == null){  
			return null;  
		}    
		Map<String, Object> map = new HashMap<String, Object>();  
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());  
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
			for (int i = 0; i< propertyDescriptors.length; i++) {  
				PropertyDescriptor descriptor = propertyDescriptors[i];  
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class") && !propertyName.equals("mallHost")) {  
					Method readMethod = descriptor.getReadMethod();  
					Object result = readMethod.invoke(bean, new Object[0]);  
					map.put(propertyName, result);  
				}  
			}  
		} catch (Exception e) {
			throw new Exception("对象转换异常", e);
		}
		return map;
	}

	/**
	 * 异常转换
	 * 
	 * @date 2015年11月18日 下午4:02:10
	 * @param e
	 * @return
	 */
//	public static String exceptionConvertStr(Exception e){
//		if(e == null){
//			return "";
//		}
//		return org.apache.commons.lang.exception.ExceptionUtils.getFullStackTrace(e);
//	}

//	public static String getImageUrl(String imageUrl){
//		if(!Utils.isEmpty(imageUrl) && imageUrl.indexOf("http") == -1){
//			if(imageUrl.indexOf("/formally/")>=0){
//				return HelperPropertie.getValue("image.read.url") + imageUrl;
//			}else if(imageUrl.indexOf("/HD/")>=0){
//				return HelperPropertie.getValue("image.read.url.hd") + imageUrl;				
//			}
//		}
//		return imageUrl;
//	}

	/**
	 * 
	 * @param imageUrl
	 * @param size  格式为: ?400_400
	 * @return
	 */
//	public static String getImageUrl(String imageUrl,String size){
//		imageUrl = getImageUrl(imageUrl);
////		if(!Utils.isEmpty(imageUrl) && !Utils.isEmpty(size)){
////			return imageUrl+size;
////		}
//		return imageUrl;
//	}
	
	/**
	 * 去掉域名
	 * @param imageUrl
	 * @return
	 */
	public static String replaceImageUrl(String imageUrl){
//		if(!Utils.isEmpty(imageUrl)){
//			String imageReadUrl = HelperPropertie.getValue("image.read.url");
//			if(!Utils.isEmpty(imageReadUrl)){
//				return imageUrl.replace(imageReadUrl.trim(), "");
//			}
//		}
		return imageUrl;
	}
	
	public static String getHostIp(HttpServletRequest request) throws Exception {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		if(ip != null && ip.startsWith("0")){
			return getRealIp();
		}
		return ip;
	}
	
	/**
	 * 随机密码
	 * @return
	 */
	public static String randomPassword(){
		String str = "abcdefghijklmnopqrstuvwxyz";
		return str.charAt(random(0,25))+random(5);
	}
	
	public static String getRealIp() throws Exception{
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
 
        try {
			Enumeration<NetworkInterface> netInterfaces = 
			    NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			boolean finded = false;// 是否找到外网IP
			while (netInterfaces.hasMoreElements() && !finded) {
			    NetworkInterface ni = netInterfaces.nextElement();
			    Enumeration<InetAddress> address = ni.getInetAddresses();
			    while (address.hasMoreElements()) {
			        ip = address.nextElement();
			        if (!ip.isSiteLocalAddress() 
			                && !ip.isLoopbackAddress() 
			                && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
			            netip = ip.getHostAddress();
			            finded = true;
			            break;
			        } else if (ip.isSiteLocalAddress() 
			                && !ip.isLoopbackAddress() 
			                && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
			            localip = ip.getHostAddress();
			        }
			    }
			}
		} catch (SocketException e) {
			throw new Exception("获取IP异常", e);
		}
     
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }
	
}
