
/**
 * The MIT License
 *
 *  Copyright (c) 2018, Lyndon Tavares (integraldominio@gmail.com)
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package org.idomine.security.rest.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

import javax.transaction.Transactional;

import org.idomine.security.model.User;
import org.idomine.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
public class UserCrudResource
{
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public Iterable<User> listaAll()
    {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<?> searchId(@PathVariable Long id)
    {
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users/count")
    public Long count()
    {
        return userRepository.count();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/users")
    @Transactional
    public ResponseEntity<User> update(@RequestBody User u)
    {
        User c = userRepository.save(u);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/users")
    @Transactional
    public ResponseEntity<User> add(@RequestBody User u)
    {
        u.setLastPasswordResetDate(new Date());
        User newUser = userRepository.save(u);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/users/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        userRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/users/foto/{id}")
    @Transactional
    public ResponseEntity<?> singleFileUpload(@PathVariable Long id, @RequestParam("file") MultipartFile file)
    {
        if (file.isEmpty())
        {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.NO_CONTENT);
        }
        try
        {
            User user = userRepository.findById(id).get();

            if (user != null)
            {
                byte[] bytes = file.getBytes();
                // salvando em arquivo
                // Path path = Paths.get("fotos/" + user.getId().toString() + "-" + file.getOriginalFilename());
                // Files.write(path, bytes);
                user.setFoto(bytes);
                userRepository.save(user);
                new ResponseEntity<>("sucesso!", HttpStatus.OK);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
