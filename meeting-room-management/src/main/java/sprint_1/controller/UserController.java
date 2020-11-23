package sprint_1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sprint_1.dto.ChangePasswordDTO;
import sprint_1.dto.UserManagerDTO;
import sprint_1.model.Role;

import sprint_1.model.User;
import sprint_1.service.RoleService;
import sprint_1.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    //--------------------------------Get All-List --------------------------------------------
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

    //-----------------------------Delete-User-By-Id ----------------------------------------
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //------------------------------ Get-User-By-Id---------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Blog with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> registerUser(@RequestBody UserManagerDTO userManagerDTO) {
        if (userService.existsByUserName(userManagerDTO.getUserName())) {
//            return ResponseEntity
//                    .badRequest()
//                    .body("Error: Username is already taken!");
//            return ResponseEntity.badRequest().body("Error: username duplicate");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        // Create new user's account
        User user = new User();
        user.setUserName(userManagerDTO.getUserName());
        user.setPassword(userManagerDTO.getPassword());
        user.setFullName(userManagerDTO.getFullName());
        user.setDepartment(userManagerDTO.getDepartment());
        user.setRole(roleService.findByRoleName(userManagerDTO.getRoleName()));
        System.err.print(userManagerDTO.getPassword());
        userService.save(user);
//        return ResponseEntity.ok("User registered successfully!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //--------------------------- Edit user--------------------------------
    @PutMapping(value = "/edit/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody UserManagerDTO userManagerDTO) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        user.setUserName(userManagerDTO.getUserName());
        user.setPassword(userManagerDTO.getPassword());
        user.setFullName(userManagerDTO.getFullName());
        user.setDepartment(userManagerDTO.getDepartment());
        user.setRole(roleService.findByRoleName(userManagerDTO.getRoleName()));
        userService.save(user);
//        return ResponseEntity.ok("User registered successfully!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //----------------------------change password-------------------------------
    @PutMapping(value = "/{id}/change-password")
    public ResponseEntity<?> changePassWordUser(@RequestBody ChangePasswordDTO changePasswordDTO, @PathVariable("id") long id) {
        User user = userService.findById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.changePassWord(id, changePasswordDTO.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //-------------------------------- Search-------------------------------
    @GetMapping(value = "/search")
    public ResponseEntity<List<UserManagerDTO>> findUserByUserNameOrDepartment(@RequestParam("input1") String input1,
                                                                               @RequestParam("input2") String input2) {
        List<User> userAllList = userService.findAll();
        List<User> searchUser = new ArrayList<>();
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
