package net.geekh.securitydemo.controller;

import net.geekh.securitydemo.domain.LoginUser;
import net.geekh.securitydemo.domain.User;
import net.geekh.securitydemo.req.RegisterReq;
import net.geekh.securitydemo.service.LoginService;
import net.geekh.securitydemo.service.user.RegisterService;
import net.geekh.securitydemo.service.user.UserInfoService;
import net.geekh.securitydemo.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @Author YIHONG
 * @Description
 * @Date 2024/3/13 07:53
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/login")
    public ResponseVo login(@RequestBody User user) {
        return loginService.login(user);
    }

    @PostMapping("/register")
    public ResponseVo register(@RequestBody RegisterReq registerReq) {
        registerService.register(registerReq);
        return new ResponseVo<>(200, "注册成功");
    }

    @GetMapping("/userInfo")
    public ResponseVo getInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getXxuser().getId();
        User user = userInfoService.getById(userId);
        return new ResponseVo(200, "查询成功", user);
    }

    @PutMapping("/userInfo")
    public ResponseVo updateUserInfo(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getXxuser().getId();

        if (!userId.equals(user.getId())) {
            throw new RuntimeException("请求参数错误");
        }
        user.setUserName(null);
        user.setEmail(null);
        user.setStatus(null);
        userInfoService.updateById(user);
        return new ResponseVo<>(200, "修改信息成功");
    }

//    @PutMapping("/userPwd")
//    public ResponseVo resetPassword(String oldPassword, String newPassword) {
//        LoginUserContextBean loginUser = SecurityContextUtil.getLoginUser();
//        String userName = loginUser.getUsername();
//        String password = loginUser.getPassword();
//        if (!SecurityContextUtil.matchesPassword(oldPassword, password)) {
//            return Result.fail("修改密码失败，旧密码错误");
//        }
//        if (SecurityContextUtil.matchesPassword(newPassword, password)) {
//            return Result.fail("新密码不能与旧密码相同");
//        }
//        if (!userService.resetUserPwd(userName, newPassword)) {
//            return Result.fail("修改密码异常，请联系管理员");
//        }
//        // 更新缓存用户密码
//        tokenService.setLoginUser(loginUser);
//        return Result.success();
//    }

//    @PreAuthorize("hasAuthority('system:test:list')")
    @GetMapping("/logout")
    public ResponseVo logout() {
        return loginService.logout();
    }


}
