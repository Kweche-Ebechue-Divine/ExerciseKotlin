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

/* Displace of different views */
                
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

            composable(route = "shopping") {
                AddCart()
            }



        }

   }
}

/* The top bar navigation view */

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

        Icon(painter = painterResource(id = R.drawable.home_icon),
            contentDescription="",
            modifier=Modifier.clickable { navController.navigate("home") }
        )


        Icon(painter = painterResource(id = R.drawable.shopping),
            contentDescription="",
            modifier=Modifier.clickable { navController.navigate("shopping") }
        )



    }

}

/* The bottom bar navigation view */
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
       Icon(painter = painterResource(id = R.drawable.menu_icon),
           contentDescription="",
       modifier=Modifier.clickable { navController.navigate( "menu") }
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

/* Different images for the menu view */
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



/* The information view of the application */
@Composable
fun InfoView() {
    Column(
        modifier=Modifier
            .padding(15.dp),


    ) {
        Text(text="INFORMATION PAGE OF THE APPLICATION")
        Text(text="Welcome to the information page of this food ordering application")
        Text(text="In this restaurant we sell  pizza, sushi, and burger")
        Text(text="Navigate to the menu list to view the varieties of the items we offer")
        Text(text="After browsing the menu list, you can navigate to the other options")
        Text(text="In order to place an order, you need to login using your user´s account")
        Text(text="Once you are login, you can fill the order form to place an order")
        Text(text="After filling the order form you proceed to make the payment")
        Text(text="Thank you for using this restaurant platform")
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



    }

}

@Composable
fun AddCart () {

AddView()
    
}


@Composable
fun AddView() {


    var item by remember {
        mutableStateOf("" )

    }

    var quantity by remember {
        mutableStateOf("" )}

    var price by remember {
        mutableStateOf("" )}



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally




    ) {
        Text(text= "ADD TO CART")
        OutlinedTextField(
            value = item ,
            onValueChange = { item=it },
            label ={ Text(text="Item ")}
        )

        OutlinedTextField(
            value = quantity,
            onValueChange ={ quantity= it },
            label ={ Text(text="Quantity")},
        )


        OutlinedTextField(
            value = price,
            onValueChange ={ price= it },
            label ={ Text(text="Price")},
        )

        OutlinedButton(onClick = { }) {
            Text(text= "Add to cart")

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


        OutlinedButton(onClick = { }) {
            Text(text= "Send")

        }



    }

}
















