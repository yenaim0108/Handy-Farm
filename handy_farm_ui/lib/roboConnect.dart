import 'package:flutter/material.dart';
import 'common_ui.dart' as cui;
import 'signup.dart' as su;


class roboConnect extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        backgroundColor: cui.bgColor,
        body:con_num(),
      ),
    );
  }
}


class con_num extends StatelessWidget {

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
                  TextSpan(text: '로보연동',
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
                    Text('* 인증방법을 선택해주세요', style: TextStyle(
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
                cui.pageButton("시리얼 번호", true, true),
                Spacer(),
                cui.pageButton("QR 코드 인증", false, true),


              ],
            ),
          ),

          Container(
            height: 10,
            //child: Placeholder()
          ),

          SafeArea(
              top: true,
              left: true,
              right: true,

              child: Container(
                margin: EdgeInsets.symmetric(horizontal: 55.0),
                height: 50.0,
                child: Row(
                  children: <Widget>[
                    cui.TextBox('시리얼 번호 입력', 210, true),

                    Spacer(),
                    su.certButton('확인', 85)
                  ],
                ),
              ),



          ),





        ],
      ),

    );

  }
}
