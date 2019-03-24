package rsa


import scala.io.StdIn

object Main extends App {
  val (e, d, n) = RSA.generateKeys(1024)
  val rsa = new RSA(e, d, n)
  println("Введите текст для шифрования:")
  var input = StdIn.readLine()
  println("Строка в байтах на входе: " + toByteString(input.getBytes))
  // encrypt
  val encrypted = rsa.encrypt(input.getBytes)
  println("Шифр: " + toByteString(encrypted))
  // decrypt
  val decrypted = rsa.decrypt(encrypted)
  println("Строка в байтах на выходе расшифрованная: " + toByteString(decrypted))
  println("Дешиврованная строка: " + new String(decrypted))

  def toByteString(arr: Array[Byte]): String = {
    arr.map(b => Integer.toString(b.toInt, 10)).reduce((str1: String, str2: String) => str1 + str2)
  }
}
