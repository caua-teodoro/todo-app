    package br.edu.todo.service;

    import br.edu.todo.dto.UserRequestDTO;
    import br.edu.todo.dto.UserResponseDTO;
    import br.edu.todo.exception.BusinessException;
    import br.edu.todo.exception.ResourceNotFoundException;
    import br.edu.todo.model.User;
    import br.edu.todo.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.UUID;

    @Service
    public class UserService {
        @Autowired
        private UserRepository userRepository;
        @Autowired
        private PasswordEncoder passwordEncoder;

        public UserResponseDTO createUser(UserRequestDTO dto) {
            if (userRepository.existsByEmail(dto.email())) {
                throw  new BusinessException("Email Already Exists");
            }

            String encodedPassword = passwordEncoder.encode(dto.password());

            User user = new User(dto.name(),  dto.email(), encodedPassword);

            User userSaved = userRepository.save(user);
            return UserResponseDTO.from(userSaved);
        }

        public UserResponseDTO findById(UUID id) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
            return UserResponseDTO.from(user);
        }
    }
