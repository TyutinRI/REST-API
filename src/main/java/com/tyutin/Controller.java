package com.tyutin;

import com.tyutin.model.User;
import com.tyutin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(UserService userService) {
        this.userService = userService;
    }


    /**
     * Метод для обработки GET запроса
     * @param id - id пользователя
     * @return пользователя с запрашиваемым id в формате JSON, или сообщение об ошибке в
     * случае отсутствия id, либо в случае отсутствия пользователя с запрашиваемым id
     */
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> readUser(@PathVariable("id") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getUserById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Метод для обработки GET запроса
     * @return список всех пользователей в формате JSON, или сообщение об ошибке, если в базе данных пусто
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> readAll(){
        List<User> userList = this.userService.getAll();

        if(userList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    /**
     * Метод для обработки Post запроса
     * @param user - получаемый из тела запроса пользователь, которого нужно записать в базу данных
     * @return сохраненного пользователя в формате JSON, или сообщение об ошибке, если в запросе
     * отсутствовали данные
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@RequestBody @Valid User user){
        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.userService.createUser(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Метод для обработки Delete запроса
     * @param id - id пользователя
     * @return удаленного пользователя в формате JSON, или сообщение об ошибке,
     * случае отсутствия id, либо в случае отсутствия пользователя с запрашиваемым id
     */
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.getUserById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.userService.deleteUserById(id);
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);


    }

    /**
     * Метод для обработки Put запроса
     * @param user получаемый из тела запроса пользователь, запись в базе данных которого нужно изменить
     * @return измененного пользователя в формате JSON, или сообщение об ошибке, если в запросе
     * отсутствовали данные
     */
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user,
                                           @PathVariable("id") Long id){
        if(user == null || id ==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User updatedUser = this.userService.updateUser(user, id);

        if(updatedUser != null){
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

}
