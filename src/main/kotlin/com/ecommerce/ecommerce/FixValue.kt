package com.ecommerce.ecommerce

const val JWT_TOKEN_VALIDITY = (5 * 60 * 60).toLong()
const val serialVersionUID = -2550185165626007488L


/**
 *  val map = hashMapOf<String, String>()
if (request.name.isEmpty()) {
map["name"] = "Name cannot be empty"
}
if (request.email.isEmpty()) {
map["email"] = "email cannot be empty"
}/*else if (request.email.isVal){}*/
if (request.password.isEmpty()) {
map["password"] = "password field cannot be empty"
}
//        if(departmentRepository.searchIdList()?.contains(request.deptId)==true){}
if (!map.isNullOrEmpty()) {
return ResponseEntity(ResBadRequest(map), HttpStatus.NOT_ACCEPTABLE)
}
val newUser=User(
name = request.name,
username = request.email,
password = request.password,
otp = Random.nextInt(111111..999999)
)
val saveUser=userRepository.save(newUser)
val resUser=ResUser(saveUser.name,saveUser.username)
return ResponseEntity(resUser,HttpStatus.OK)
}
/**Get UserDetails*/
@GetMapping("/getall")
fun getAllUser():MutableIterable<User>{
return userRepository.findAll()
}

/**User Login Details
fun login(@ModelAttribute request:ReqLogin):ResponseEntity<*>{
val existingUser:UserRepository.searchByEmail(email)
}*/
 */
