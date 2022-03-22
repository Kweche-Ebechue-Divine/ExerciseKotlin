package com.example.ddapplication

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Composable
fun MainView() {

    val navController = rememberNavController()
    Scaffold(
        topBar = {TopBarView(navController) },
    bottomBar = { BottomBarView(navController) },


                
        ){
        NavHost(navController = navController, startDestination = "home") {
         composable(route = "home") {
             HomeView()
               }

            composable(route = "info") {
                InfoView()

                  }

            composable(route = "login") {
               LoginMainView()
            }

            composable(route = "menu") {
                MenuView()
            }



        }

   }
}



@Composable
fun TopBarView(navController: NavHostController) {
    Row(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .height(45.dp)
            .background(Color(0xFF33D4FF)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically


    ) {

        Icon(painter = painterResource(id = R.drawable.menu_icon),
            contentDescription="",
            modifier=Modifier.clickable { navController.navigate("menu") }
        )

          OutlinedTextField(
              value = "",
              onValueChange = {},
          trailingIcon = {

                  Icon(painter = painterResource(id = R.drawable.search),
                      contentDescription="")
},
              modifier = Modifier
                  .border(1.dp, Color.Black)
                  .background(Color.White)

              )


    }

}
@Composable
fun BottomBarView(navController: NavHostController) {
   Row(
       modifier = Modifier
           .fillMaxWidth()
           .height(45.dp)
           .background(Color(0xFF33D4FF)),
           horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically


   ) {
       Icon(painter = painterResource(id = R.drawable.home_icon),
           contentDescription="",
       modifier=Modifier.clickable { navController.navigate( "home") }
       )

       Icon(painter = painterResource(id = R.drawable.info_icon),
           contentDescription="",
                   modifier=Modifier.clickable { navController.navigate("info") }
       )


       Icon(painter = painterResource(id = R.drawable.login_icon),
           contentDescription="",
                   modifier=Modifier.clickable { navController.navigate("login") }
       )

   }
}


@Composable
fun MenuView() {
    val images= arrayOf(
        R.drawable.pizza1,
        R.drawable.pizza2,
        R.drawable.sushi1,
        R.drawable.sushi2,
        R.drawable.sushi3,
        R.drawable.sushi4,

    )


    LazyColumn() {
       items(images){resId ->
           Img(resId)

    }
        
    }

}



@Composable
fun Img(res: Int) {
    Image (painter = painterResource(res), contentDescription ="",
    modifier= Modifier
        .size(380.dp,380.dp)

        )

}




@Composable
fun InfoView() {
    Column() {
        Text(text="INFORMATION PAGE OF THE APPLICATION")
        Text(text="Welcome to the information page of this food ordering application")
        Text(text="In this restaurant we sell sell pizza, sushi, and burger")
        Text(text="Navigate to the menu list to view the varieties of the items we offer")
        Text(text="-After browsing the menu list, you can navigate to the other options")
        Text(text="-By using this application you can login using your user´s account")
        Text(text="-You can browse the menu page of food items offered by this restaurant")
        Text(text="After browsing the menu list, you can order a food item of your choice")
        Text(text=" Login and fill the order form if you want place an order")
    }


}

@Composable
fun HomeView() {


    Column() {

        Text(text= "CUSTOMER FOOD ORDERING APPLICTION")

        Image (painter = painterResource(R.drawable.reslogo), contentDescription ="",

            modifier= Modifier
                .fillMaxSize()

        )

    }
}

@Composable
fun LoginMainView() {
    val loginViewModel =viewModel<NewViewModel>()
    if(loginViewModel.username.value.isEmpty()){

        LoginView(loginViewModel)

    } else {
    Text(text = "Successful login")
        OrderView()

    }

}




@Composable
fun LoginView(loginViewModel:NewViewModel) {
    var email by remember {
        mutableStateOf("" )

    }

    var password by remember {
        mutableStateOf("" )}




    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally




    ) {
        Text(text= "LOGIN PAGE")
      OutlinedTextField(
          value = email ,
          onValueChange = { email=it },
          label ={ Text(text="Email")}
      )


        OutlinedTextField(
            value = password,
            onValueChange ={ password= it },
            label ={ Text(text="Password")},
            visualTransformation = PasswordVisualTransformation())
        OutlinedButton(onClick = {loginViewModel.userLogin(email,password) }) {
            Text(text= "Login")

        }

        OutlinedButton(onClick = { /*For login to firebase*/ }) {
            Text(text= "SignUp")

        }

    }

}



@Composable
fun OrderView() {
    var firstname by remember {
        mutableStateOf("" )

    }

    var lastname by remember {
        mutableStateOf("" )}

    var address by remember {
        mutableStateOf("" )}

    var city by remember {
        mutableStateOf("" )}

    var country by remember {
        mutableStateOf("" )}

    var email by remember {
        mutableStateOf("" )}

    var telephone by remember {
        mutableStateOf("" )}


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally




    ) {
        Text(text= "ORDER FORM")
        OutlinedTextField(
            value = firstname ,
            onValueChange = { firstname=it },
            label ={ Text(text="First name")}
        )


        OutlinedTextField(
            value = lastname,
            onValueChange ={ lastname= it },
            label ={ Text(text="Last name")},
            )

        OutlinedTextField(
            value = address,
            onValueChange ={ address= it },
            label ={ Text(text="Address")},
        )

        OutlinedTextField(
            value = city,
            onValueChange ={ city= it },
            label ={ Text(text="City")},
        )

        OutlinedTextField(
            value = country,
            onValueChange ={country= it },
            label ={ Text(text="Country")},
        )

        OutlinedTextField(
            value = email,
            onValueChange ={ email= it },
            label ={ Text(text="Email")},
        )

        OutlinedTextField(
            value = telephone,
            onValueChange ={ telephone= it },
            label ={ Text(text="Telephone")}
        )
        OutlinedButton(onClick = { }) {
            Text(text= "Login")

        }

        OutlinedButton(onClick = { /*For login to firebase*/ }) {
            Text(text= "SignUp")

        }

    }

}
















