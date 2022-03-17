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

                  }

            composable(route = "login") {
               LoginView()
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
fun HomeView() {
    Text(
        text = "FOOD APPLICATION",
    fontSize = 42.sp,
        modifier = Modifier.background(Color.Green)


    )


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
fun LoginView() {
    var email by remember{mutableStateOf("")}
    var pw by remember{mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {
    OutlinedTextField(
        value = "Email",
        onValueChange ={email= it},
        label ={ Text(text="Email")}
    )
        

        OutlinedTextField(
            value = "pw",
            onValueChange ={pw= it},
            label ={ Text(text="Password")},
            visualTransformation = PasswordVisualTransformation())
        OutlinedButton(onClick = { /*For login to firebase*/ }) {
            Text(text= "Login")

        }

    }

}






