import 'package:flutter/material.dart';
import 'device_scale.dart' as ds;

Color hfGreen = Color(0xFF22AA3D);
Color hfGray = Color(0xFFB7B7B7);
Color hfYellow = Color(0xFFF5AA00);
Color hfRed = Color(0xFFE8474B);
Color hfLiteGreen = Color(0xFFAACC03);
Color bgColor = Color(0xFFF5F5F3);
Color titleText = Color(0xFF000000);




Widget TextBox(String text, int size, bool isShort){

  double wLength;
  Color _bgColor = Color(0xFFFFFFFF);

  // 버튼 길이 지정
  if (isShort)
    wLength = 180.0 * ds.deviceScale;
  else
    wLength = 270.0 * ds.deviceScale;

  return Container(
    child: Row(

      children: <Widget>[
        ButtonTheme(
            height: 43.0 * ds.deviceScale,
            minWidth: size * ds.deviceScale,
            child: RaisedButton (
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(30.0 * ds.deviceScale),
                  side: BorderSide(color: _bgColor, width: 2.0 * ds.deviceScale)
              ),
              textColor: Color(0xFFD1D1D1),
              color: _bgColor,
              padding: EdgeInsets.all(8.0 * ds.deviceScale),
              onPressed: () {},

              child: Container(
                width: wLength,
                child: Text(text, textAlign :TextAlign.left , style: TextStyle(
                    fontSize: 17.0 * ds.deviceScale,
                    fontWeight: FontWeight.w600 ),
                ),
              )
            )
        )
      ],
    ),
  );


}

Widget pageButton(String text, bool isSelectedPage, bool isShort) {
  Color backgroundColor;
  double wLength;

  // 버튼 배경색 지정
  if (isSelectedPage)
    backgroundColor = hfGreen;
  else
    backgroundColor = hfGray;

  // 버튼 길이 지정
  if (isShort)
    wLength = 145.0 * ds.deviceScale;
  else
    wLength = 170.0 * ds.deviceScale;

  return Container (
    child: Row(
      children: <Widget>[
        ButtonTheme(
          height: 43.0 * ds.deviceScale,
          minWidth: wLength * ds.deviceScale,
            child: RaisedButton (
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(30.0 * ds.deviceScale),
                  side: BorderSide(color: backgroundColor, width: 2.0 * ds.deviceScale)
              ),
              textColor: Color(0xFFFFFFFF),
              color: backgroundColor,
              padding: EdgeInsets.all(8.0 * ds.deviceScale),
              onPressed: () {},

              child: Text(
                text,
                style: TextStyle(fontSize: 17.0 * ds.deviceScale, fontWeight: FontWeight.w600),
              ),
            )
        )
      ],
    ),
  );
}

Widget interactionButton(String text, bool isConfirm, bool isShort) {
  Color backgroundColor;
  Color borderColor;
  Color textColor;
  double wLength;

  // 버튼 스타일 지정
  if (isConfirm) {
    backgroundColor = hfGreen;
    borderColor = hfGreen;
    textColor = Color(0xFFFFFFFF);
  }
  else {
    backgroundColor = bgColor;
    borderColor = hfGreen;
    textColor = hfGreen;
  }

  // 버튼 길이 지정
  if (isShort)
    wLength = 145.0 * ds.deviceScale;
  else
    wLength = 300.0 * ds.deviceScale;

  return Container (
    child: Row(
      children: <Widget>[
        ButtonTheme(
            height: 43.0 * ds.deviceScale,
            minWidth: wLength * ds.deviceScale,
            child: RaisedButton (
              shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(30.0 * ds.deviceScale),
                  side: BorderSide(color: borderColor, width: 2.0 * ds.deviceScale)
              ),
              textColor: textColor,
              color: backgroundColor,
              padding: EdgeInsets.all(8.0 * ds.deviceScale),
              onPressed: () {},

              child: Text(
                text,
                style: TextStyle(fontSize: 17.0 * ds.deviceScale, fontWeight: FontWeight.w600),
              ),
            )
        )
      ],
    ),
  );
}