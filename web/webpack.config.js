const CopyWebpackPlugin = require('copy-webpack-plugin')
const HtmlWebPackPlugin = require("html-webpack-plugin")
const path = require("path")
const webpack = require("webpack")

module.exports = (env, argv) => ({
    devServer: {
        proxy: {
            '/api': {
                target: 'https://discover-ar-dev.herokuapp.com',
                secure: false,
                changeOrigin: true
            }
        },
    },
    output: {
        path: path.resolve(__dirname, 'build')
    },
    devtool: "#eval-source-map",
    module:
        {
            rules: [
                {
                    test: /\.(js|jsx)$/,
                    exclude: /node_modules/,
                    use: {
                        loader: "babel-loader"
                    }
                },
                {
                    test: /\.html$/,
                    use: [
                        {
                            loader: "html-loader"
                        }
                    ]
                },
                { test: /\.(css|s[ac]ss)$/,
                    use: [
                        { loader: 'style-loader' },
                        { loader: 'css-loader' },
                        { loader: 'sass-loader'}
                    ]
                },
                {
                    test: /\.(png|jpe?g|gif|svg)$/i,
                    loader: 'file-loader',
                    options: {
                        publicPath: 'assets',
                    },
                },
            ]
        }
    ,
    plugins: [
        new HtmlWebPackPlugin({
            template: "./public/index.html",
            filename: "index.html"
        }),
        new webpack.DefinePlugin({
            "process.env": {
                NODE_ENV: JSON.stringify(argv.mode)
            }
        }),
        new CopyWebpackPlugin([
            { from: 'public' }
        ])
    ]
});