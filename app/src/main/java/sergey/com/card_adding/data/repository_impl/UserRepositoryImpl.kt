package sergey.com.card_adding.data.repository_impl

import sergey.com.card_adding.data.repository.UserRepository
import sergey.com.card_adding.data.service.UserService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userService: UserService) : UserRepository