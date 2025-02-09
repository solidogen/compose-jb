package com.sample.content

import androidx.compose.runtime.*
import androidx.compose.web.attributes.ATarget
import androidx.compose.web.attributes.AttrsBuilder
import androidx.compose.web.attributes.Tag
import androidx.compose.web.attributes.target
import androidx.compose.web.css.*
import androidx.compose.web.elements.*
import com.sample.components.ContainerInSection
import com.sample.style.*
import org.w3c.dom.HTMLElement

@Composable
fun Intro() {
    ContainerInSection {
        Div(attrs = {
            classes(WtRows.wtRow, WtRows.wtRowSizeM, WtRows.wtRowSmAlignItemsCenter)
        }) {

            Div(attrs = {
                classes(WtCols.wtCol2, WtCols.wtColMd3)
            }, style = {
                alignSelf(AlignSelf.Start)
            }) {
                Img(src = "i1.svg", attrs = { classes(AppStylesheet.composeLogo) }) {}
            }

            Div(attrs = {
                classes(
                    WtCols.wtCol10,
                    WtCols.wtColMd8,
                    WtCols.wtColSm12,
                    WtOffsets.wtTopOffsetSm12
                )
            }) {
                H1(attrs = { classes(WtTexts.wtHero) }) {
                    Text("Compose for ")
                    Span(style = {
                        display(DisplayStyle.InlineBlock)
                        property("white-space", value("nowrap"))
                    }, attrs = { classes(WtTexts.wtHero) }) {
                        Text("Web")

                        Span(attrs = { classes(AppStylesheet.composeTitleTag) }) {
                            Text("Technology preview")
                        }
                    }
                }
                Div(attrs = {
                    classes(WtDisplay.wtDisplayMdNone)
                }) {
                    IntroAboutComposeWeb()
                }
            }
        }


        Div(attrs = {
            classes(WtDisplay.wtDisplayNone, WtDisplay.wtDisplayMdBlock)
        }) {
            IntroAboutComposeWeb()
        }
    }
}

@Composable
private fun IntroAboutComposeWeb() {
    Div(attrs = {
        classes(WtRows.wtRow, WtRows.wtRowSizeM)
    }) {

        Div(attrs = {
            classes(WtCols.wtCol9, WtCols.wtColMd9, WtCols.wtColSm12)
        }) {
            P(attrs = { classes(WtTexts.wtSubtitle2, WtOffsets.wtTopOffset24) }) {
                Text("Reactive web UIs for Kotlin, based on Google's ")

                A(href = "https://developer.android.com/jetpack/compose", attrs = {
                    classes(WtTexts.wtLink)
                    target(ATarget.Blank)
                }) {
                    Text("modern toolkit")
                }

                Text(" and brought to you by JetBrains")
            }

            P(attrs = {
                classes(WtTexts.wtText1, WtOffsets.wtTopOffset24)
            }) {
                Text(
                    "Compose for Web simplifies and accelerates UI development for web applications, " +
                            "and aims to enable UI code sharing between web, desktop, and Android applications " +
                            "in the future. Currently in technology preview."
                )
            }

            ComposeWebStatusMessage()

            IntroCodeSample()

            A(
                attrs = {
                    classes(WtTexts.wtButton, WtOffsets.wtTopOffset24)
                    target(ATarget.Blank)
                },
                href = "https://github.com/jetbrains/compose-jb"
            ) {
                Text("Explore on GitHub")
            }
        }
    }
}

@Composable
private fun IntroCodeSample() {
    Div(style = {
        marginTop(24.px)
        backgroundColor(Color.RGBA(39, 40, 44, 0.05))
        borderRadius(8.px)
        property("font-family", value("'JetBrains Mono', monospace"))
    }) {
        Div(style = {
            property("padding", value("12px 16px"))
        }) {
            FormattedCodeSnippet(
                code = """
                fun greet() = listOf("Hello", "Hallo", "Hola", "Servus").random()

                renderComposable("greetingContainer") {
                    var greeting by remember { mutableStateOf(greet()) }
                    Button(attrs = { onClick { greeting = greet() } }) {
                        Text(greeting)
                    }
                } 
            """.trimIndent()
            )
        }

        Hr(style = {
            height(1.px)
            border(width = 0.px)
            backgroundColor(Color.RGBA(39, 40, 44, 0.15))
        })

        IntroCodeSampleResult()
    }
}

@Composable
private fun IntroCodeSampleResult() {
    Div(style = {
        property("padding", value("12px 16px"))
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Row)
        alignItems(AlignItems.Center)
    }) {
        Span(
            attrs = { classes(WtTexts.wtText2) },
            style = {
                property("margin-right", value(8.px))
            }
        ) {
            Text("Result:")
        }

        fun greet() = listOf("Hello", "Hallo", "Hola", "Servus").random()

        Div(attrs = {
            id("greetingContainer")
        }) {
            var greeting by remember { mutableStateOf(greet()) }
            Button(attrs = { onClick { greeting = greet() } }) {
                Text(greeting)
            }
        }
    }
}

@Composable
private fun ComposeWebStatusMessage() {
    Div(attrs = {
        classes(WtRows.wtRow, WtRows.wtRowSizeXs, WtOffsets.wtTopOffset24)
    }) {
        Div(attrs = {
            classes(WtCols.wtColInline)
        }) {
            Img(src = "ic_info.svg", style = {
                width(24.px)
                height(24.px)
            }) {}
        }

        Div(attrs = {
            classes(WtCols.wtColAutoFill)
        }) {
            P(attrs = {
                classes(WtTexts.wtText3)
            }) {
                Text(
                    "With its current status Technology Preview, Compose for Web " +
                            "is not production-ready, and should only be used in experiments. " +
                            "We are hard at work to bring you great learning materials, tutorials, " +
                            "and documentation, and optimize the performance of Compose for Web in the future!"
                )
            }
        }
    }
}

@Composable
inline fun Hr(
    crossinline attrs: (AttrsBuilder<Tag.Div>.() -> Unit) = {},
    crossinline style: (StyleBuilder.() -> Unit) = {},
) {
    TagElement<Tag.Div, HTMLElement>(
        tagName = "hr",
        applyAttrs = attrs,
        applyStyle = style,
        content = { }
    )
}