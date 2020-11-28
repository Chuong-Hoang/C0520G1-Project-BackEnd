package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.ChangePasswordDTO;
import sprint_1.dto.UserManagerDTO;

import sprint_1.model.User;
import sprint_1.service.RoleService;
import sprint_1.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * UserController
 * <p>
 * Version 1.0
 * <p>
 * Date: 24-11-2020
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 22-11-2020         HienTH           CRUD
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * get data for User list page
     *
     * @param
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<UserManagerDTO>> getListUser() {
        List<User> userList = userService.findAll();
        List<UserManagerDTO> newList = new ArrayList<>();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (User user : userList) {
                newList.add(new UserManagerDTO(user.getIdUser(), user.getUserName(), user.getFullName(), user.getDepartment(), user.getRole().getRoleName()));
            }
            return new ResponseEntity<>(newList, HttpStatus.OK);
        }
    }

    /**
     * delete asset by idUser
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * get data for User list page
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * create user
     *
     * @param userManagerDTO
     * @return
     */
    @PostMapping(value = "/create")
    public ResponseEntity<?> registerUser(@Validated({UserManagerDTO.checkCreate.class, UserManagerDTO.checkEdit.class})
                                          @RequestBody UserManagerDTO userManagerDTO, BindingResult bindingResult) {
        if (userService.existsByUserName(userManagerDTO.getUserName())) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (bindingResult.hasErrors()) {

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        User user = new User();
        user.setUserName(userManagerDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userManagerDTO.getPassword()));
        user.setFullName(userManagerDTO.getFullName());
        user.setDepartment(userManagerDTO.getDepartment());
        user.setRole(roleService.findByRoleName(userManagerDTO.getRoleName()));
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * edit user
     *
     * @param userManagerDTO
     * @return
     */
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<?> updateUser(@Validated({UserManagerDTO.checkEdit.class, UserManagerDTO.checkNewPassword.class}) @PathVariable("id") long id,
                                        @RequestBody UserManagerDTO userManagerDTO, BindingResult bindingResult) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        user.setUserName(userManagerDTO.getUserName());
        user.setPassword(passwordEncoder.encode(userManagerDTO.getNewPassword()));
        user.setFullName(userManagerDTO.getFullName());
        user.setDepartment(userManagerDTO.getDepartment());
        user.setRole(roleService.findByRoleName(userManagerDTO.getRoleName()));
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * change password user
     *
     * @param changePasswordDTO
     * @return
     */
    @PutMapping(value = "/{id}/change-password")
    public ResponseEntity<?> changePassWordUser(@Validated @RequestBody ChangePasswordDTO changePasswordDTO,
                                                @PathVariable("id") long id) {
        User user = userService.findById(id);
        List<ChangePasswordDTO> errorsList = new ArrayList<>();
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (BCrypt.checkpw(changePasswordDTO.getOldPassword(), user.getPassword())) {
            userService.changePassWord(id, passwordEncoder.encode(changePasswordDTO.getNewPassword()));
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            errorsList.add(new ChangePasswordDTO("Mật khẩu không chính xác"));
            return new ResponseEntity<>(errorsList, HttpStatus.OK);
        }
    }

    /**
     * search user by userName or by department containing
     *
     * @param input1,input2
     * @return
     */
    @GetMapping(value = "/search")
    public ResponseEntity<List<UserManagerDTO>> findUserByUserNameOrDepartment(@RequestParam("input1") String input1,
                                                                               @RequestParam("input2") String input2) {
        List<User> userAllList = userService.findAll();
        List<User> searchUser = null;
        if (userAllList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if ("".equals(input1) && "".equals(input2)) {
            searchUser = userAllList;
        } else if ("".equals(input1)) {
            searchUser = userService.findUserByDepartmentContaining(input2);
        } else if ("".equals(input2)) {
            searchUser = userService.findUserByUserNameContaining(input1);
        } else {
            searchUser = userService.findUserByUserNameContainingAndDepartmentContaining(input1, input2);
        }
        List<UserManagerDTO> searchList = new ArrayList<>();
        for (User user : searchUser) {
            searchList.add(new UserManagerDTO(user.getIdUser(), user.getUserName(), user.getFullName(), user.getDepartment(), user.getRole().getRoleName()));
        }
        if (searchList.isEmpty()) {
            return new ResponseEntity<>(searchList, HttpStatus.OK);
        }
        return new ResponseEntity<>(searchList, HttpStatus.OK);
    }
}
