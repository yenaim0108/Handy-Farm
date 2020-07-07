import 'package:flutter/material.dart';
import 'common_ui.dart' as cui;
import 'device_scale.dart' as ds;


class signup extends StatelessWidget {


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        backgroundColor: cui.bgColor,
        body:signupNew(),
      ),
    );
  }

}

class signupNew extends StatelessWidget {

  Widget certButton(String text, int size){
    Color _backgroundColor;
    _backgroundColor= Color(0xFFFFFFFF);
    return Container(
      child: Row(

        children: <Widget>[
          ButtonTheme(
              height: 43.0 * ds.deviceScale,
              minWidth: size * ds.deviceScale * ds.deviceScale,
              child: RaisedButton (
                shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(30.0 * ds.deviceScale),
                    side: BorderSide(color: _backgroundColor, width: 2.0 * ds.deviceScale)
                ),
                textColor: Color(0xFF22AA4D),
                color: _backgroundColor,
                padding: EdgeInsets.all(8.0 * ds.deviceScale),
                onPressed: () {},

                child: Text(text, style: TextStyle(
                    fontSize: 17.0 * ds.deviceScale,
                    fontWeight: FontWeight.w800
                ),
                ),
              )
          )
        ],
      ),
    );

  }

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
                  TextSpan(text: '회원가입',
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
                certButton('인증', 85)
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



          SafeArea(
              top: true,
              left: true,
              right: true,
              child: Container(
                margin: EdgeInsets.symmetric(horizontal: 16.0),
                height: 50.0,
              )
          ),

          Container(
            margin: EdgeInsets.symmetric(horizontal: 55.0),
            child: Row(
              children: <Widget>[
                cui.interactionButton('다음', true, false)
              ],
            ),
          )




        ],
      ),

    );

  }
}
