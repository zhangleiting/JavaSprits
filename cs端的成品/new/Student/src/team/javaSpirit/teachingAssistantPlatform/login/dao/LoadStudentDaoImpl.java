package team.javaSpirit.teachingAssistantPlatform.login.dao;

import org.hibernate.Session;

import team.javaSpirit.teachingAssistantPlatform.entity.LoadStudent;
import team.javaSpirit.teachingAssistantPlatform.entity.Students;
import team.javaSpirit.teachingAssistantPlatform.util.HibernateUtil;

/**
 * 
 * <p>
 * Title: StudentsDaoImpl
 * </p>
 * <p>
 * Description: Students数据库操作
 * </p>
 * 
 * @author renyuyuan
 * @date 2018年12月10日
 */
public class LoadStudentDaoImpl {
	public void saveLoadStudent(LoadStudent ls) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		session.save(ls);
		session.getTransaction().commit();
	}

	/**
	 * <p>
	 * Title: getStudentById
	 * </p>
	 * <p>
	 * Description: 根据Students主键sid查找Students对象
	 * </p>
	 * 
	 * @param sid
	 * @return Students
	 */
	public Students getStudentById(String sid) {
		Session session = HibernateUtil.getSession();
		return session.get(Students.class, sid);
	}

	/**
	 * 
	 * <p>
	 * Title: updateStudentIp
	 * </p>
	 * <p>
	 * Description:修改学表Students的ip保持到数据库
	 * </p>
	 * 
	 * @param ip
	 */
	public Students updateStudentIp(Students s, String ip) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Students p = session.get(Students.class, s.getSid());
		p.setIp(ip);
//		session.update(p);可写可不写
		session.getTransaction().commit();
		return p;
	}

	public void updateStudentPassword(Students s, String pwd) {
		Session session = HibernateUtil.getSession();
		session.beginTransaction();
		Students p = session.get(Students.class, s.getSid());
		p.setPassword(pwd);
//		session.update(p);可写可不写
		session.getTransaction().commit();
	}
}
