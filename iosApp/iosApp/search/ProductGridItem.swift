//
//  SwiftUIView.swift
//  iosApp
//
//  Created by Максим Цыганий on 30.10.23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

@available(iOS 15.0, *)
struct ProductGridItem: View {
    let product: ProductItem
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8){
            ZStack{
                AsyncImage(
                    url: URL(string: product.imageUrl)
                ) { image in
                    image.resizable()
                } placeholder: {
                    Color.gray
                }
                
            }
            .frame(
                maxWidth: .infinity,
                idealHeight: .infinity
            )
            .clipShape(RoundedRectangle(cornerRadius: 8))
            
            Text(product.title)
                .font(.title3)
                .fontWeight(.bold)
                .lineLimit(1)
        }
        .frame(maxWidth: .infinity, maxHeight: 260)
    }
}
