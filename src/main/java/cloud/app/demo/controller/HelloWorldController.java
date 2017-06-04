package cloud.app.demo.controller;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cloud.app.demo.dao.ClientReqInfoDao;
import cloud.app.demo.entity.ClientReqInfo;

@RestController
public class HelloWorldController {

	@Autowired
	private ClientReqInfoDao clientReqInfoDao;

	@RequestMapping("/hello")
	public String greeting(HttpServletRequest request) {
		String jvmName = ManagementFactory.getRuntimeMXBean().getName();
		String clientIP = getClientIPAddress(request);
		// save client ip and timestamp into database
		ClientReqInfo reqInfo = new ClientReqInfo();
		reqInfo.setClientIP(clientIP);
		reqInfo.setAccessTime(new Date());
		reqInfo.setJvmName(jvmName);
		reqInfo = clientReqInfoDao.save(reqInfo);

		return "This demo app is running on jvm: " + jvmName;
	}

	@RequestMapping("/history")
	public List<ClientReqInfo> getRequestHistory() {
		return clientReqInfoDao.findAll();
	}

	private String getClientIPAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
