package com.hwj.xm.service.impl;

import com.hwj.xm.constant.P2PConstant;
import com.hwj.xm.db.dao.*;
import com.hwj.xm.po.*;
import com.hwj.xm.service.IBasUserService;
import com.hwj.xm.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class BasUserServiceImpl implements IBasUserService{
    @Autowired
    private BasUserDao basUserDao;
    @Resource
    private BasUserInfoDao basUserInfoDao;
    @Resource
    private BasUserSecurityDao basUserSecurityDao;
    @Resource
    private BusAccountDao busAccountDao;
    @Resource
    private BusUserIntegralDao busUserIntegralDao;
    @Resource
    private BusIncomeStatDao busIncomeStatDao;
    @Resource
    private BusUserStatDao busUserStatDao;
    @Resource
    private BasExperiencedGoldDao basExperiencedGoldDao;
    @Resource
    private SysLogDao sysLogDao;


    @Override
    public BasUser queryBasUserByMobile(String phone) {
        return basUserDao.queryBasUserByMobile(phone);
    }


    public void saveUser(String phone, String password) {
        /**
         *表名	描述
         bas_user	用户基本信息(主表)
         bas_user_info	用户信息扩展表记录添加
         bas_user_security	用户安全信息表
         bus_account	用户账户表记录信息
         bus_user_integral	用户积分记录
         bus_income_stat	用户收益表记录
         bus_user_stat	用户统计表
         bas_experienced_gold	注册体验金表
         sys_app_settings
         sys_log	app表信息
         *
         */
        /**
         * 1.参数校验
         *    参数非空  手机号唯一
         * 2.用户记录添加
         *    用户记录信息初始化
         * 3.结果返回
         */
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空!");
        AssertUtil.isTrue(null!=basUserDao.queryBasUserByMobile(phone),"该手机号已注册!");
        Integer userId= initBasUser(phone,password);
        // 初始化用户基本信息
        initBasUserInfo(userId);
        // 用户安全信息表
        initBasUserSecurity(userId);
        // 用户账户信息初始化
        initBusAccount(userId);
        // 用户基本初始化方法
        initBusUserIntegral(userId);
        // 用户收益初始化
        initBusIncomeStat(userId);
        // 初始化用户统计信息
        initBusUserStat(userId);
        //  用户体验今信息初始化
        initBasExperiencedGold(userId);
        // 初始化系统日志
        initSysLog(userId);




    }

    @Override
    public BasUser userLogincheck(String phone, String password) {
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号不能为空!");
        AssertUtil.isTrue(StringUtils.isBlank(password),"密码不能为空!");
        BasUser temp=basUserDao.queryBasUserByMobile(phone);
        AssertUtil.isTrue(null==temp,"该手机号不存在!");
        String salt=temp.getSalt();
        password=MD5.toMD5(password+salt);
        AssertUtil.isTrue(!password.equals(temp.getPassword()),"密码不正确!");
        temp.setLastLoginIp(IpUtils.get());
        temp.setLastLoginTime(new Date());
        AssertUtil.isTrue(basUserDao.update(temp)<1, P2PConstant.OP_FAILED_MSG);
        return temp;
    }

    private void initSysLog(Integer userId) {
        SysLog sysLog=new SysLog();
        sysLog.setOperating("用户注册");
        sysLog.setAddtime(new Date());
        sysLog.setAddip(IpUtils.get());
        sysLog.setCode("register");
        sysLog.setResult(1);
        sysLog.setType(4);
        sysLog.setUserId(userId);
        AssertUtil.isTrue(sysLogDao.insert(sysLog)<1,P2PConstant.OP_FAILED_MSG);
    }

    private void initBasExperiencedGold(Integer userId) {
        BasExperiencedGold basExperiencedGold=new BasExperiencedGold();
        basExperiencedGold.setWay("register");
        basExperiencedGold.setUserId(userId);
        basExperiencedGold.setUsefulLife(10);
        basExperiencedGold.setStatus(2);
        basExperiencedGold.setRate(BigDecimal.valueOf(1));
        basExperiencedGold.setGoldName("注册体验金");
        basExperiencedGold.setExpiredTime(DateUtils.addDays(new Date(),30));
        basExperiencedGold.setAmount(BigDecimal.valueOf(2888));
        basExperiencedGold.setAddtime(new Date());
        basExperiencedGold.setAddip(IpUtils.get());
        AssertUtil.isTrue(basExperiencedGoldDao.insert(basExperiencedGold)<1,P2PConstant.OP_FAILED_MSG);

    }

    private void initBusUserStat(Integer userId) {
        BusUserStat busUserStat=new BusUserStat();
        busUserStat.setUserId(userId);
        busUserStat.setRechargeCount(0);
        busUserStat.setRechargeAmount(BigDecimal.ZERO);
        busUserStat.setInvestLaveAmount(BigDecimal.ZERO);
        busUserStat.setInvestCount(0);
        busUserStat.setInvestAmount(BigDecimal.ZERO);
        busUserStat.setCouponCount(0);
        busUserStat.setCouponAmount(BigDecimal.ZERO);
        busUserStat.setCashCount(0);
        busUserStat.setCashAmount(BigDecimal.ZERO);
        AssertUtil.isTrue(busUserStatDao.insert(busUserStat)<1,P2PConstant.OP_FAILED_MSG);
    }


    private void initBusIncomeStat(Integer userId) {
        BusIncomeStat busIncomeStat=new BusIncomeStat();
        busIncomeStat.setWaitIncome(BigDecimal.ZERO);
        busIncomeStat.setUserId(userId);
        busIncomeStat.setTotalIncome(BigDecimal.ZERO);
        busIncomeStat.setEarnedIncome(BigDecimal.ZERO);
        AssertUtil.isTrue(busIncomeStatDao.insert(busIncomeStat)<1,P2PConstant.OP_FAILED_MSG);
    }

    private void initBusUserIntegral(Integer userId) {
        BusUserIntegral busUserIntegral=new BusUserIntegral();
        busUserIntegral.setTotal(0);
        busUserIntegral.setUsable(0);
        busUserIntegral.setUserId(userId);
        AssertUtil.isTrue(busUserIntegralDao.insert(busUserIntegral)<1,P2PConstant.OP_FAILED_MSG);
    }

    private void initBusAccount(Integer userId) {
        BusAccount busAccount=new BusAccount();
        busAccount.setWait(BigDecimal.ZERO);
        busAccount.setUserId(userId);
        busAccount.setUsable(BigDecimal.ZERO);
        busAccount.setTotal(BigDecimal.ZERO);
        busAccount.setRepay(BigDecimal.ZERO);
        busAccount.setFrozen(BigDecimal.ZERO);
        busAccount.setCash(BigDecimal.ZERO);
        AssertUtil.isTrue(busAccountDao.insert(busAccount)<1,P2PConstant.OP_FAILED_MSG);
    }

    private void initBasUserSecurity(Integer userId) {
        BasUserSecurity basUserSecurity=new BasUserSecurity();
        basUserSecurity.setPhoneStatus(1);
        basUserSecurity.setUserId(userId);
        AssertUtil.isTrue(basUserSecurityDao.insert(basUserSecurity)<1,P2PConstant.OP_FAILED_MSG);;
    }

    private void initBasUserInfo(Integer userId) {
        BasUserInfo basUserInfo=new BasUserInfo();
        basUserInfo.setUserId(userId);
        basUserInfo.setCustomerType(0);
        String code= RandomCodesUtil.createRandom(false,5);
        basUserInfo.setInviteCode(code);
        basUserInfo.setCashLimit(0);
        AssertUtil.isTrue(basUserInfoDao.insert(basUserInfo)<1,P2PConstant.OP_FAILED_MSG);
    }

    /**
     * 用户基本信息添加
     * @param phone
     * @param password
     */
    private Integer initBasUser(String phone,String password) {
        BasUser basUser=new BasUser();
        //basUser.setUsername("sxt_p2p_"+UUID.randomUUID().toString().replace());
        basUser.setType(1);
        basUser.setStatus(1);
        String salt=RandomCodesUtil.createRandom(false,4);
        password= MD5.toMD5(password+salt);
        basUser.setSalt(salt);
        basUser.setReferer("PC");
        basUser.setPassword(password);
        basUser.setMobile(phone);
        basUser.setAddtime(new Date()) ;
        basUser.setAddip(IpUtils.get());
        AssertUtil.isTrue(basUserDao.insert(basUser)<1, P2PConstant.OP_FAILED_MSG);
        basUser.setUsername("SXT_P2P"+basUser.getId());
        AssertUtil.isTrue(basUserDao.update(basUser)<1,P2PConstant.OP_FAILED_MSG);
        return basUser.getId();
    }
}
