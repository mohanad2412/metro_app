package com.example.metrotest

import android.content.Intent
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.size
import mumayank.com.airlocationlibrary.AirLocation

class MainActivity : AppCompatActivity(), AirLocation.Callback {

    lateinit var textShow:TextView
    lateinit var viewButton: Button
    lateinit var spinner1:Spinner
    lateinit var spinner2:Spinner
    lateinit var Location:AirLocation
    lateinit var getLoc:Button
    lateinit var myLocation:Location
    lateinit var locView: TextView

//    val allStations= listOf(
//        "Helwan ",
//        "Ain Helwan ",
//        "Helwan University ",
//        "Wadi Hof ",
//        "Hadayek Helwan ",
//        "El-Maasara ",
//        "Tora El-Asmant ",
//        "Kozzika ",
//        "Tora El-Balad ",
//        "Sakanat El-Maadi ",
//        "Maadi  ",
//        "Hadayek El-Maadi ",
//        "Dar El-Salam ",
//        "El-Zahraa' ",
//        "Mar Girgis ",
//        "El-Malek El-Saleh ",
//        "Al-Sayeda Zeinab ",
//        "Saad Zaghloul ",
//        "Sadat ",
//        "Nasser ",
//        "Orabi ",
//        "Al-Shohadaa ",
//        "Ghamra ",
//        "El-Demerdash ",
//        "Manshiet El-Sadr ",
//        "Kobri El-Qobba ",
//        "Hammamat El-Qobba ",
//        "Saray El-Qobba ",
//        "Hadayeq El-Zaitoun ",
//        "Helmeyet El-Zaitoun ",
//        "El-Matareyya ",
//        "Ain Shams ",
//        "Ezbet El-Nakhl ",
//        "El-Marg ",
//        "New El-Marg ",
//        "El-Mounib ",
//        "Sakiat Mekky ",
//        "Omm El-Masryeen ",
//        "El Giza ",
//        "Faisal ",
//        "Cairo University ",
//        "El Bohoth ",
//        "Dokki ",
//        "Opera ",
//        "Sadat ",
//        "Mohamed Naguib ",
//        "Attaba ",
//        "Al-Shohadaa ",
//        "Masarra ",
//        "Road El-Farag ",
//        "St. Teresa ",
//        "Khalafawy ",
//        "Mezallat ",
//        "Kolleyyet El-Zeraa ",
//        "Shubra El-Kheima")
//
    val firstLine = listOf(
        "Helwan",
        "Ain Helwan",
        "Helwan University",
        "Wadi Hof",
        "Hadayek Helwan",
        "El-Maasara",
        "Tora El-Asmant",
        "Kozzika",
        "Tora El-Balad",
        "Sakanat El-Maadi",
        "Maadi",
        "Hadayek El-Maadi",
        "Dar El-Salam",
        "El-Zahraa'",
        "Mar Girgis",
        "El-Malek El-Saleh",
        "Al-Sayeda Zeinab",
        "Saad Zaghloul",
        "Sadat",
        "Nasser",
        "Orabi",
        "Al-Shohadaa",
        "Ghamra",
        "El-Demerdash",
        "Manshiet El-Sadr",
        "Kobri El-Qobba",
        "Hammamat El-Qobba",
        "Saray El-Qobba",
        "Hadayeq El-Zaitoun",
        "Helmeyet El-Zaitoun",
        "El-Matareyya",
        "Ain Shams",
        "Ezbet El-Nakhl",
        "El-Marg",
        "New El-Marg"
    )
    val secondLine = listOf(
        "El-Mounib",
        "Sakiat Mekky",
        "Omm El-Masryeen",
        "El Giza",
        "Faisal",
        "Cairo University",
        "El Bohoth",
        "Dokki",
        "Opera",
        "Sadat",
        "Mohamed Naguib",
        "Attaba",
        "Al-Shohadaa",
        "Masarra",
        "Road El-Farag",
        "St. Teresa",
        "Khalafawy",
        "Mezallat",
        "Kolleyyet El-Zeraa",
        "Shubra El-Kheima"
    )
//    val thirdLine = listOf(
//        "Airport",
//        "Ahmed Galal",
//        "Adly Mansour",
//        "El Haykestep",
//        "Omar Ibn El-Khattab",
//        "Qobaa",
//        "Hesham Barakat",
//        "El-Nozha",
//        "Nadi El-Shams",
//        "Alf Maskan",
//        "Heliopolis Square",
//        "Haroun",
//        "Al-Ahram",
//        "Koleyet El-Banat",
//        "Stadium",
//        "Fair Zone",
//        "Abbassia",
//        "Abdou Pasha",
//        "El Geish",
//        "Bab El Shaaria",
//        "Attaba",
//        "Nasser",
//        "Maspero",
//        "Safaa Hegazy",
//        "Kit Kat",
//        "Sudan Street",
//        "Imbaba",
//        "El-Bohy",
//        "Al-Qawmeya Al-Arabiya",
//        "Ring Road",
//        "Rod al-Farag Axis",
//        "El-Tawfikeya",
//        "Wadi El-Nil",
//        "Gamaat El Dowal Al-Arabiya",
//        "Bulaq El-Dakroor",
//        "Cairo University"
//    )
    var allStations= mutableListOf<String>()
    var stationLocation= mutableListOf<Float>()
    val stations= arrayOf(
        Station("Helwan",29.78822,31.30178),
         Station("Ain Helwan",	29.86782,	31.31559),
         Station("Helwan University",	29.78822,	31.30178),
         Station("Wadi Hof",	30.38217,	30.35481),
         Station("Hadayek Helwan",	29.78822,	31.30178),
         Station("El-Maasara",	31.29756,	31.27027),
         Station("Tora El-Asmant",	29.92364,	31.28423),
         Station("Kozzika",	30.11666,	31.31367),
         Station("Tora El-Balad",	29.94616,	31.26919),
         Station("Sakanat El-Maadi",	29.95554,	31.2551),
         Station("Maadi",	29.95554,	31.2551),
         Station("Hadayek El-Maadi",	29.9527,	31.26535),
         Station("Dar El-Salam",	26.27919,	32.05916),
         Station("El-Zahraa'",	31.4972,	31.14491),
         Station("Mar Girgis",	25.68962,	32.64634),
         Station("El-Malek El-Saleh",	30.01591,	31.22886),
         Station("Al-Sayeda Zeinab",	29.92051,	31.23983),
         Station("Saad Zaghloul",	30.1236,	31.14489),
         Station("Sadat",	30.35861,	30.49401),
         Station("Nasser",	29.9939,	31.12986),
         Station("Orabi",	30.14375,	31.24672),
         Station("Al-Shohadaa",	30.59557,	30.89985),
         Station("Ghamra",	30.71326,	31.25955),
         Station("El-Demerdash",	30.39718,	31.51476),
         Station("Manshiet El-Sadr",	30.08483,	31.28783),
         Station("Kobri El-Qobba",	30.08683,	31.29225),
         Station("Hammamat El-Qobba",	30.09733,	31.30691),
         Station("Saray El-Qobba",	30.09733,	31.30691),
         Station("Hadayeq El-Zaitoun",	30.11666,	31.31367),
         Station("Helmeyet El-Zaitoun",	30.11311,	31.31347),
         Station("El-Matareyya",	30.11666,	31.31367),
         Station("Ain Shams",	30.11666,	31.31367),
         Station("Ezbet El-Nakhl",	28.00374,	30.83966),
         Station("El-Marg",	30.16612,	31.38339),
         Station("New El-Marg",	28.08467,	30.81285),
         Station("Sakiet Mekky",24.4825,32.9153),
         Station("om el masreyeen",30.04738,31.26404),
         Station("Giza",30.16898,31.07461),
         Station("Faisal",30.16204,32.32235),
         Station("Cairo University",30.96151,29.54267),
         Station("Al Behoos",30.11666,31.31367),
         Station("Dokki",30.11679,	31.61706),
         Station("Opera",30.08549,31.29165),
         Station("Sadat",30.35861	,30.49401),
         Station("Mohamed Naguib",31.2349,29.97621),
         Station("Attaba",30.11666,31.31367),
         Station("Al Shohadaa",30.59557	,30.89985),
         Station("Massara",30.11666,31.31367),
         Station("Road el Farag",30.07761,31.23777),
         Station("Saint Teresa",28.56492,33.95055),
         Station("Khalafawy",31.2124,	31.70295),
         Station("Mezallat",30.11666,31.31367),
         Station("Koleyet el zera3a",30.92146,	30.12832),
         Station("Shubra el khaima",30.12346,	31.23632)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textShow=findViewById(R.id.textShow)
        viewButton=findViewById(R.id.viewButton)
        spinner1=findViewById(R.id.spinner1)
        spinner2=findViewById(R.id.spinner2)
        getLoc=findViewById(R.id.getLoc)
        locView=findViewById(R.id.locView)
        val a=ArrayAdapter(this,android.R.layout.simple_list_item_1,allStations)
        spinner1.adapter=a
        val b=ArrayAdapter(this,android.R.layout.simple_list_item_1,allStations)
        spinner2.adapter=b

        val Location=AirLocation(this,this,true,0,"")
        Location.start()

        allStations.addAll(firstLine)
        allStations.addAll(secondLine)
    }
    fun view(view: View) {
        val start=spinner1.selectedItem.toString()
        val end= spinner2.selectedItem.toString()
        val output1 = firstLine.indexOf(start)
        val output2 = firstLine.indexOf(end)
        val output3 = secondLine.indexOf(start)
        val output4 = secondLine.indexOf(end)
//        val output5 = thirdLine.indexOf(start)
//        val output6 = thirdLine.indexOf(end)

        if (start in firstLine && end in firstLine) {
            if (output1 > output2) {
                var lineResult = firstLine.slice(output1 downTo output2).toList()
                textShow.append("$lineResult\n")
                textShow.append("you will take ${lineResult.size} station in the firstLine\n")
                if (lineResult.size <= 9) {
                    textShow.append("your ticket price is 5\n")
                } else if (lineResult.size <= 15) {
                    textShow.append("your ticket price is 7\n")
                } else if (lineResult.size > 15) {
                    textShow.append("your ticket price is 15\n")
                }
            } else if (output1 < output2) {
                var lineResult1 = firstLine.slice(output1..output2).toList()
                textShow.append("$lineResult1 \n")
                textShow.append("you will take ${lineResult1.size} station in the firstLine\n")
                if (lineResult1.size <= 9) {
                    textShow.append("your ticket price is 5\n")
                } else if (lineResult1.size <= 15) {
                    textShow.append("your ticket price is 7\n")
                } else if (lineResult1.size > 15) {
                    textShow.append("your ticket price is 15\n")
                }
            }
        } else if (start in secondLine && end in secondLine) {
            if (output3 < output4) {
                var result3 = secondLine.slice(output3..output4).toList()
                textShow.append("$result3 \n")
                textShow.append("you will take ${result3.size} stations in the secondLine\n")
                if (result3.size <= 9) {
                    textShow.append("your ticket price is 5 egp\n")
                } else if (result3.size <= 15) {
                    textShow.append("your ticket price is 7egp\n")
                } else if (result3.size > 15) {
                    textShow.append("your ticket price is 10 egp\n")
                }
            } else if (output3 > output4) {
                var result4 = secondLine.slice(output3 downTo output4).toList()
                textShow.append("$result4 \n")
                textShow.append("you will take ${result4.size} station in the secondLine\n")
                if (result4.size <= 9) {
                    textShow.append("your ticket price is 5 egp\n")
                } else if (result4.size <= 15) {
                    textShow.append("your ticket price is 7egp\n")
                } else if (result4.size > 15) {
                    textShow.append("your ticket price is 10 egp\n")
                }

            }
//        } else if (start in thirdLine && end in thirdLine) {
//            if (output5 < output6) {
//                var result5 = thirdLine.slice(output5..output6).toList()
//                textShow.append("$result5 \n")
//                textShow.append("you will take ${result5.size} station in the thirdLine\n")
//                if (result5.size <= 9) {
//                    textShow.append("your ticket price is 5 egp\n")
//                } else if (result5.size <= 15) {
//                    textShow.append("your ticket price is 7egp\n")
//                } else if (result5.size > 15) {
//                    textShow.append("your ticket price is 10 egp\n")
//                }
//            } else if (output5 > output6) {
//                var result6 = thirdLine.slice(output5 downTo output6).toList()
//                textShow.setText("$result6 \n")
//                textShow.append("you will take ${result6.size} station in the thirdLine\n")
//                if (result6.size <= 9) {
//                    textShow.append("your ticket price is 5 egp\n")
//                } else if (result6.size <= 15) {
//                    textShow.append("your ticket price is 7egp\n")
//                } else if (result6.size > 15) {
//                    textShow.append("your ticket price is 10 egp\n")
//                }
//            }
        }else if (start in firstLine && end in secondLine) {
            var result7=firstLine.slice(output1..18).toList()
            var result8=firstLine.slice(output1 downTo 18).toList()
            var result9=secondLine.slice(9 .. output4).toList()
            var result11=secondLine.slice(9 downTo output4).toList()
            if (output1<18 && output4<9) {
                if (output1<output4) {
                    textShow.append("$result7 \n")
                    textShow.append("you will take ${result7.size} stations in the firstLine\n")
                    textShow.append("$result11 \n")
                    textShow.append("you will take ${result11.size} stations in the secondLine\n")
                    var totalStations=result7.size+result11.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                } else if (output1>output4) {
                    textShow.append("$result7 \n")
                    textShow.append("you will take ${result7.size} stations in the firstLine\n")
                    textShow.append("$result11 \n")
                    textShow.append("you will take ${result11.size} stations in tthe secondLine\n")
                    var totalStations=result7.size+result11.size
                    if (totalStations <= 9) {
                        println("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        println("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        println("your ticket price is 10 egp\n")
                    }
                }
            } else if (output1>18 && output4>9) {
                if (output1>output4) {
                    textShow.append("$result8 \n")
                    textShow.append("you will take ${result8.size} stations in the firstLine\n")
                    textShow.append("$result9 \n")
                    textShow.append("you will take ${result9.size} stations in the secondLine\n")
                    var totalStations=result8.size+result9.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                } else if (output1<output4) {
                    textShow.append("$result8 \n")
                    textShow.append("you will take ${result8.size} stations in the firstLine\n")
                    textShow.append("$result9 \n")
                    textShow.append("you will take ${result9.size} stations in the secondLine\n")
                    var totalStations=result8.size+result9.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                }
            } else if (output1<18 && output4>9) {
                if (output1<output4) {
                    textShow.append("$result7 \n")
                    textShow.append("you will take ${result7.size} stations in the firsLine\n")
                    textShow.append("$result9 \n")
                    textShow.append("you will take ${result9.size} stations in the secondLine\n")
                    var totalStations=result7.size+result9.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                } else if (output1>output4) {
                    textShow.append("$result7 \n")
                    textShow.append("you will take ${result7.size} stations in the firsLine\n")
                    textShow.append("$result9 \n")
                    textShow.append("you will take ${result9.size} stations in the secondLine\n")
                    var totalStations=result7.size+result9.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                }
            } else if (output1>18 && output4<9) {
                textShow.append("$result8 \n")
                textShow.append("you will take ${result8.size} stations in the firstLine\n")
                textShow.append("$result11 \n")
                textShow.append("you will take ${result11.size} stations in the secondLine\n")
                var totalStations=result7.size+result11.size
                if (totalStations <= 9) {
                    textShow.append("your ticket price is 5 egp\n")
                } else if (totalStations <= 15) {
                    textShow.append("your ticket price is 7egp\n")
                } else if (totalStations > 15) {
                    textShow.append("your ticket price is 10 egp\n")
                }
            }
        } else if (start in secondLine && end in firstLine) {
            var result12=secondLine.slice(output3..9).toList()
            var result13=secondLine.slice(output3 downTo 9).toList()
            var result14=firstLine.slice(18..output2).toList()
            var result15=firstLine.slice(18 downTo output2).toList()
            if (output3<9 && output2<18) {
                if (output3<output2) {
                    textShow.append("$result12 \n")
                    textShow.append("you will take ${result12.size} stations in the secondLine\n")
                    textShow.append("$result15 \n")
                    textShow.append("you will take ${result15.size} stations in the firstLine\n")
                    var totalStations=result12.size+result15.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                } else if (output3>output2) {
                    textShow.append("$result12 \n")
                    textShow.append("you will take ${result12.size} stations in the secondLine\n")
                    textShow.append("$result15 \n")
                    textShow.append("you will take ${result15.size} stations in the firstLine\n")
                    var totalStations=result12.size+result15.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                }
            } else if (output3>9 && output2>18) {
                if (output3<output2) {
                    textShow.append("$result13 \n")
                    textShow.append("you will take ${result13.size} stations in the secondLine\n")
                    textShow.append("$result14 \n")
                    textShow.append("you will take ${result14.size} stations in the firstLine\n")
                    var totalStations=result13.size+result14.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                } else if (output3>output4) {
                    textShow.append("$result13 \n")
                    textShow.append("you will take ${result13.size} stations in the secondLine\n")
                    textShow.append("$result14 \n")
                    textShow.append("you will take ${result14.size} stations in the firstLine\n")
                    var totalStations=result13.size+result14.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                }
            } else if (output3>9 && output2<18) {
                if (output3>output2) {
                    textShow.append("$result13\n")
                    textShow.append("you will take ${result13.size} stations in the secondLine\n")
                    textShow.append("$result15 \n")
                    textShow.append("you will take ${result15.size} stations in the firstLine\n")
                    var totalStations=result13.size+result15.size
                    if (totalStations <= 9) {
                        textShow.append("your ticket price is 5 egp\n")
                    } else if (totalStations <= 15) {
                        textShow.append("your ticket price is 7egp\n")
                    } else if (totalStations > 15) {
                        textShow.append("your ticket price is 10 egp\n")
                    }
                }
            } else if (output3<9 && output2>18) {
                textShow.append("$result12 \n")
                textShow.append("you will take ${result12.size} stations in the secondLine\n")
                textShow.append("$result14 \n")
                textShow.append("you will take ${result14.size} stations in the firstLine\n")
                var totalStations=result12.size+result14.size
                if (totalStations <= 9) {
                    textShow.append("your ticket price is 5 egp\n")
                } else if (totalStations <= 15) {
                    textShow.append("your ticket price is 7egp\n")
                } else if (totalStations > 15) {
                    textShow.append("your ticket price is 10 egp\n")
                }
            }
        }
    }
    fun swip(view: View) {
        val list1=spinner1.selectedItemPosition
        val list2=spinner2.selectedItemPosition
        spinner1.setSelection(list2)
        spinner2.setSelection(list1)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Location.onActivityResult(requestCode, resultCode, data) // ADD THIS LINE INSIDE onActivityResult
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Location.onRequestPermissionsResult(requestCode, permissions, grantResults) // ADD THIS LINE INSIDE onRequestPermissionResult
    }
    override fun onFailure(locationFailedEnum: AirLocation.LocationFailedEnum) {
        Toast.makeText(this, "Incorrect Value", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccess(locations: ArrayList<Location>) {
        val lat=locations[0].latitude
        val long=locations[0].longitude
        myLocation=locations[0]
        val coder = Geocoder(this)
        repeat(stations.size) {
            val stationLoc = Location("")
            stationLoc.latitude=stations[it].lat
            stationLoc.longitude=stations[it].long
            val distance = stationLoc.distanceTo(myLocation)
            stationLocation.add(distance)
            locView.append("\n ${distance / 1000}-- ${stations[it].name}")
            textShow.text="${locations[0].latitude} --  ${locations[0].longitude}"
            val a=Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${locations[0].latitude}, ${locations[0].longitude}"))
            startActivity(a)
        }
        val m=stationLocation.min()
        val index=stationLocation.indexOf(m)
        println("${allStations[index]}")
    }
    fun getLoc(view: View) {

    }
}
