import SwiftUI
import shared

struct SearchScreen: View {
    @StateObject var viewModel = SearchScreenViewModel()
    
    let gridColumns: [GridItem] = Array(repeating: GridItem(.flexible(), spacing: 16), count: 2)
    
    var body: some View {
        NavigationStack{
            
            ScrollView{
                LazyVGrid(columns: gridColumns, spacing: 16){
                    
                    ForEach(viewModel.products, id: \.id){ product in
                        
                        NavigationLink(value: product){
                            ProductGridItem(product: product)
                                .task {
                                    if product == viewModel.products.last && !viewModel.isLoading && !viewModel.loadFinished {
                                        await viewModel.loadProducts()
                                    }
                                }
                        }
                        .buttonStyle(PlainButtonStyle())
                    }
                    
                    if viewModel.isLoading {
                        Section(footer: ProgressView()){}
                    }
                    
                }
                .padding(.horizontal, 12)
                
            }
            .navigationTitle("Products")
            
        }
        .task {
            await viewModel.loadProducts()
        }
    }
}
