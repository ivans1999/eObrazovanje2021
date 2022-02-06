package ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.ChangePassDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.JwtDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.LoginDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.RoleDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.dtos.UserDTO;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.exceptions.BadRequestException;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Account;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Administrator;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Role;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Student;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.Teacher;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.User;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.model.UserRole;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.security.TokenUtils;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AccountServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.AdministratorServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.StudentServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.TeacherServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserRoleServiceInterface;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.RoleServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.UserServiceI;
import ftn.projekat.eObrazovanje.ProjekatEObrazovanje20212022.serviceInterface.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping(value = "/api")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired 
	private UserServiceI userService;
	
	@Autowired
	private StudentServiceI studentS;
	
	@Autowired
	private AdministratorServiceI adminS;
	
	@Autowired
	private TeacherServiceI teachS;
	
	@Autowired
	private AccountServiceI accountS;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleServiceI roleS;
	
	@Autowired
	private UserRoleServiceInterface userRoleS;
	
	@Autowired
	private StudentServiceI studentService;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<JwtDTO> login(@RequestBody LoginDTO loginDTO) {
		System.out.println("\nLogin-------<<<<");
        try {
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
					loginDTO.getUsername(), loginDTO.getPassword());
            Authentication authentication = authenticationManager.authenticate(token);
            UserDetails details = userDetailsService.loadUserByUsername(loginDTO.getUsername());
            JwtDTO t = new JwtDTO(tokenUtils.generateToken(details));
            return new ResponseEntity<JwtDTO>(t, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.status(401).build();
        }
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO dto, UriComponentsBuilder ucBuilder){
		User existUser = this.userService.findByUsername(dto.getUserName());
		System.out.println("Metoda se pozvala");
		if (existUser != null) {
			return ResponseEntity.status(409).build();
		}
		User user = new User();
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setUsername(dto.getUserName());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user = userService.save(user);
		for (RoleDTO roleDTO : dto.getRoles()) {
			Role r = roleS.findByCode(roleDTO.getCode());
			UserRole userRole = new UserRole(user,r);
			user.getUserRoles().add(userRole);
			if(roleDTO.getCode().equals("st")) {
				Student student = new Student();
				Date date = new Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				String cardNumber = "S-"+studentS.maxId()+"-"+calendar.get(Calendar.YEAR);
				student.setCardNumber(cardNumber);
				student.setUser(user);
				studentS.save(student);
				
				Account account = new Account();
				account.setStudent(student);
				accountS.save(account);
			}else if(roleDTO.getCode().equals("teach")) {
				Teacher teacher = new Teacher();
				teacher.setUser(user);
				teachS.save(teacher);
			}else if(roleDTO.getCode().equals("admin")) {
				Administrator admin = new Administrator();
				admin.setUser(user);
				adminS.save(admin);
			}
			user = userService.save(user);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/change-password", method = RequestMethod.POST)
	public ResponseEntity<UserDTO> changePassword(@RequestBody ChangePassDTO passwordChanger) {
		System.out.println(passwordChanger.toString() + "pwd changer");
		userDetailsService.changePassword(passwordChanger.getOldPass(), passwordChanger.getNewPass());
		
		User u = userService.findByUsername(passwordChanger.getUserName());
		u.setFirstName(passwordChanger.getFirstName());
		u.setLastName(passwordChanger.getLastName());
		u = userService.save(u);
		return new ResponseEntity<UserDTO>(new UserDTO(u), HttpStatus.OK);
	}

	
	@GetMapping(value = "/users")
	public ResponseEntity<List<UserDTO>> getAllUsers(Pageable page){
		System.out.println("\ngetAllUsers");
		Page<User> users = userService.findAll(page);
		
		List<UserDTO> dtos = new ArrayList<UserDTO>();
		System.out.println("Broj elemenata: "+users.getNumberOfElements()+"\n");
		for (User u : users) {
			dtos.add(new UserDTO(u));
		}
		return new ResponseEntity<List<UserDTO>>(dtos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/number-users")
	public ResponseEntity<Long> getNumberPage(@RequestParam String mode){
		Long num = (long)0;
		if(mode.equals("USERS")) {
			num = userService.count()/5;
			Long mod = userService.count()%5;
			if(mod>0) {
				num ++;
			}
		}else if(mode.equals("STUDENTS")) {
			num = studentService.count()/5;
			Long mod = studentService.count()%5;
			if(mod>0) {
				num ++;
			}
		}
		else if(mode.equals("TEACHERS")) {
			num = teachS.count()/5;
			Long mod = teachS.count()%5;
			if(mod>0) {
				num ++;
			}
		}
		
		return new ResponseEntity<Long>(num, HttpStatus.OK);
	}
	
	@GetMapping(value = "users/{id}")
	public ResponseEntity<UserDTO> getOneUser(@PathVariable("id") Long id){
		
		System.out.println(id + " user get id");
		User user = userService.findById(id);
		
		if(user == null) {
			System.out.println("user je null");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "users/loggedUser")
	public ResponseEntity<UserDTO> getLoggedUser(Principal principal){
		
		System.out.println(principal.getName() + " user get id");
		User user = userService.findByUsername(principal.getName());
		
		if(user == null) {
			System.out.println("user je null");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "users/{username}/unassigned-roles")
	public ResponseEntity<List<RoleDTO>> getRoles(@PathVariable("username") String username){
		User user = userService.findByUsername(username);
//		if(user == null) {
//			System.out.println("user je null");
//			return new ResponseEntity<List<RoleDTO>>(HttpStatus.NOT_FOUND);
//		}
		
		List<RoleDTO> rolesDTO = new ArrayList<RoleDTO>();
		List<Role> allRole= roleS.findAll();
		
		if(user != null && user.getUserRoles().size()>0) {
			for (Role role : allRole) {
				int isIn = 0;
				for (UserRole userRole: user.getUserRoles()) {
					if(!userRole.getRole().getCode().equals(role.getCode())) {
						System.out.println("Rola: "+role.getName()+" ne postoji kod korisnika: "+user.getUsername());
						isIn ++;
					}
				}
				System.out.println("\nIs IN: "+isIn);
				System.out.println("Size userRoles: "+user.getUserRoles().size());
				if(isIn==user.getUserRoles().size()) {
					System.out.println("Prosao\n");
					rolesDTO.add(new RoleDTO(role));
				}
			}
		}else if ((user == null) || user.getUserRoles().size()==0){
			for (Role role : allRole) {
				rolesDTO.add(new RoleDTO(role));
			}
		}
		
		return new ResponseEntity<List<RoleDTO>>(rolesDTO, HttpStatus.OK);
	}
	
	@PutMapping(value = "/users")
	@PreAuthorize("hasAnyRole('ROLE_STUDENT', 'ROLE_ADMINISTRATOR')")
	@Transactional
	public ResponseEntity<UserDTO> updateStudent(@RequestBody UserDTO userDTO){
		User user = userService.findByUsername(userDTO.getUserName());
		System.out.println("\nFirst name: "+userDTO.getFirstName());
		if(user == null) {
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		List<UserRole> userRoles = new ArrayList<UserRole>();
//		for (UserRole userRole : user.getUserRoles()) {
//			userRoleS.deleteUserRole(userRole);
//		}
		for(UserRole r:user.getUserRoles()) {
			if(r.getRole().getCode().equals("st")) {
				Student s = studentS.findByUser(user.getUsername());
				System.out.println("\nroleToString "+userDTO.roleToString());
				System.out.println("r.getRole().getCode() "+r.getRole().getCode());
				System.out.println("Da li tacno? "+userDTO.roleToString().contains(r.getRole().getCode()));
				if(s!=null && !userDTO.roleToString().contains(r.getRole().getCode())) {	
					System.out.println("\nBrisem studenta");
					studentS.delete(s.getId());
				}
			}else if(r.getRole().getCode().equals("admin")) {
				Administrator a = adminS.findByUser(user.getUsername());
				if(a!=null && !userDTO.roleToString().contains(r.getRole().getCode())) {
					adminS.delete(a.getId());
				}
			}else if(r.getRole().getCode().equals("teach") ) {
				Teacher teacher = teachS.findByUsername(user.getUsername());;
				if(teacher!=null && !userDTO.roleToString().contains(r.getRole().getCode())) {
					System.out.println("\nBrisem teachera");
					teachS.delete(teacher.getId());
				}
			}
		}
		
		userRoleS.deleteByUser(user.getId());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		if(!userDTO.getPassword().equals(user.getPassword())) {
			user.setPassword(passwordEncoder.encode(userDTO.getPassword()));	
		}
		user.setUserRoles(userRoles);
		System.out.println("\nPukao2");
		for (RoleDTO roleDTO : userDTO.getRoles()) {
			Role r = roleS.findByCode(roleDTO.getCode());
			UserRole userRole = new UserRole(user,r);
			user.getUserRoles().add(userRole);
			if(roleDTO.getCode().equals("st")) {
				Student student = studentS.findByUser(user.getUsername());
				if(student==null) {
					student = new Student();
					Date date = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					String cardNumber = "S-"+studentS.maxId()+"-"+calendar.get(Calendar.YEAR);
					student.setCardNumber(cardNumber);
					student.setUser(user);
					studentS.save(student);
					
					Account account = new Account();
					account.setStudent(student);
					accountS.save(account);
				}
			}else if(roleDTO.getCode().equals("teach")) {
				Teacher teacher = teachS.findByUsername(user.getUsername());
				if(teacher==null) {
					teacher = new Teacher();
					teacher.setUser(user);
					teachS.save(teacher);
				}
			}else if(roleDTO.getCode().equals("admin")) {
				Administrator admin = adminS.findByUser(user.getUsername());
				if(admin==null) {
					admin = new Administrator();
					admin.setUser(user);
					adminS.save(admin);
				}
			}
			user = userService.save(user);
		}
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping(value = "users/{id}")
//	@PreAuthorize("hasAnyRole('ROLE_ADMINISTRATOR')")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
		User student = userService.findById(id);
		if(student != null) {
			userService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/logOut", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> logoutUser() {
		System.out.println("\nLog out!");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)){
            SecurityContextHolder.clearContext();

            return new ResponseEntity<>("You successfully logged out!", HttpStatus.OK);
        } else {
            throw new BadRequestException("User is not authenticated!");
        }
    }
}
