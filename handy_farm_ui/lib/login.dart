import 'package:flutter/material.dart';
import 'common_ui.dart' as cui;
import 'signup.dart' as su;


class login extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        backgroundColor: cui.bgColor,
        body:Userlogin(),
      ),
    );
  }
}

class Userlogin extends StatelessWidget {

  var _isChecked = false;

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      backgroundColor: cui.bgColor,
      body: Column(
        children: <Widget>[
          SafeArea(
              top: true,
              left: true,
              right: true,
              child: Container(
                  margin: EdgeInsets.symmetric(horizontal: 16.0),
                  height: 130.0
              )
          ),

          RichText(
            text: TextSpan(
                children: [
                  TextSpan(text: '로그인',
                      style: TextStyle(
                        fontSize: 28.0,
                        color: cui.titleText,
                        fontWeight: FontWeight.w600,
                      ))
                ]
            ),
          ),

          SafeArea(
              top: true,
              left: true,
              right: true,
              child: Container(
                margin: EdgeInsets.symmetric(horizontal: 16.0),
                height: 30.0,
                /*child: Placeholder(),
                      Row(
                        children: <Widget>[
                          Text('hello')
                        ],
                      ),*/
              )
          ),

          SafeArea(
              top: true,
              left: true,
              right: true,
              child: Container(
                margin: EdgeInsets.symmetric(horizontal: 55.0),
                height: 30.0,
                child: Row(
                  children: <Widget>[
                    Text('전화번호', style: TextStyle(
                        fontSize: 20,
                        color: Color(0xFF3D3D3D),
                        fontWeight: FontWeight.w800
                    ),
                    ),

                    Spacer(), //오른쪽으로 보내기

                    Container(
                      height: 32,
                      width: 32,
                      //child: Placeholder()
                    ),
                  ],
                ),
              )

          ),

          Container(
            margin: EdgeInsets.symmetric(horizontal: 55.0),
            height: 50.0,
            child: Row(
              children: <Widget>[
                cui.TextBox('- 없이 입력', 210, true),

                Spacer(),
                su.certButton('인증', 85)
              ],
            ),
          ),

          Container(
            height: 10,
            //child: Placeholder()
          ),

          SafeArea(
              top: false,
              left: true,
              right: true,

              child: Container(
                margin: EdgeInsets.symmetric(horizontal: 55.0),
                height: 30.0,
                child: Row(
                  children: <Widget>[
                    Text('인증번호', style: TextStyle(
                        fontSize: 20,
                        color: Color(0xFF3D3D3D),
                        fontWeight: FontWeight.w800
                    ),
                    ),

                    Spacer(), //오른쪽으로 보내기

                    Container(
                      height: 32,
                      width: 32,
                      //child: Placeholder()
                    ),
                  ],
                ),
              )

          ),

          Container(
            margin: EdgeInsets.symmetric(horizontal: 55.0),
            child: Row(
              children: <Widget>[
                cui.TextBox('6자리 숫자 입력', 300, false),
              ],
            ),
          ),

          Container(
            child: Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                Checkbox(value: false, onChanged: null),
                Text('로그인 유지', style: TextStyle(fontSize: 17, color: Color(0xFF9E9E9E), fontWeight: FontWeight.w800),)
              ],
            ),
            margin: EdgeInsets.symmetric(horizontal: 45.0),
            height: 50.0,

          ),

          SafeArea(
              top: true,
              left: true,
              right: true,
              child: Container(
                margin: EdgeInsets.symmetric(horizontal: 16.0),
                height: 30.0,
              )
          ),


          Container(
            margin: EdgeInsets.symmetric(horizontal: 55.0),
            child: Row(
              children: <Widget>[
                cui.interactionButton("회원가입", false, true),
                Spacer(),
                cui.interactionButton("로그인", true, true)
              ],
            ),
          )




        ],
      ),

    );

  }
}
